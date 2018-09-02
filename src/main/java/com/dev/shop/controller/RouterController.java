package com.dev.shop.controller;

import com.dev.shop.exceptions.AlreadyPurchased;
import com.dev.shop.exceptions.EmptyValueExceptions;
import com.dev.shop.exceptions.PasswordIsInvalid;
import com.dev.shop.exceptions.SessionIsExpried;
import com.dev.shop.mongo.Users;
import com.dev.shop.repository.ProductRepository;
import com.dev.shop.repository.UserRepository;
import com.dev.shop.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.*;


@RestController
@RequestMapping("/router")
@ResponseBody
public class RouterController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    private String token = "EqdmPh53c9x33EygXpTpcoJvc4VXLK";

    @RequestMapping(value = "/purchase", method = RequestMethod.POST)
    public HashMap<String, String> purchase(@RequestBody Map<String, Object> params, HttpSession session) {

        HashMap<String, String> result = new HashMap<>();
        String message = "";
        String code = "0";
        try {
            code = "-1";
            message = "세션이 만료되었습니다.";
            if (session.getAttribute("user_session") == null) {
                throw new Exception();
            }
            String user_session = session.getAttribute("user_session").toString();
            byte[] decoded_bytes = Base64.getDecoder().decode(user_session);
            String decodedString = new String(decoded_bytes);
            String _id = decodedString.split(":")[1];
            Users user = userRepository.findOne(_id);
            ArrayList<String> purchased = user.getUserPurchase();

            String product_id = params.get("id").toString();
            System.out.print(params.toString());
            code = "-2";
            message = "이미 구매한 상품입니다.";
            if (purchased.contains(product_id)) {
                throw new Exception();
            }
            purchased.add(product_id);
            user.setUserPurchase(purchased);
            userRepository.save(user);

            result.put("result", "0");
            result.put("message", "상품 구입이 완료되었습니다.");
        } catch (Exception e) {
            result.put("result", code);
            result.put("message", message);
            return result;
        }

        return result;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public HashMap<String, String> login(@RequestBody Map<String, Object> params, HttpSession session) {
        String user_id = params.get("user_id").toString().trim();
        String user_password = params.get("user_password").toString().trim();
        Users user = this.userRepository.findByUserId(user_id);

        HashMap<String, String> result = new HashMap<>();
        try {
            if (!this.userRepository.existsByUserId(user_id)) {
                throw new Exception("입력하신 id가 없습니다.");
            }
            if (!PasswordUtils.verifyUserPassword(user_password, user.getUserPassword(), this.token)) {
                throw new Exception("비밀 번호가 틀렸습니다.");
            }

        } catch (Exception e) {
            result.put("result", "error");
            result.put("message", e.getMessage());
            return result;
        }
        result.put("result", "success");
        result.put("message", "로그인 되었습니다.");

        String user_session = user.getUserId() + ":" + user.getId();
        user_session = Base64.getEncoder().encodeToString(user_session.getBytes());
        session.setAttribute("user_session", user_session);

        return result;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST, produces = "application/json")
    public HashMap<String, String> signup(@RequestBody Map<String, Object> params) throws EmptyValueExceptions {
        String user_id = params.get("user_id").toString().trim();
        String user_password = params.get("user_password").toString().trim();
        String user_confirm_password = params.get("user_confirm_password").toString().trim();
        String user_nickname = params.get("user_nickname").toString().trim();
        String user_email = params.get("user_email").toString().trim();
        String user_name = params.get("user_name").toString().trim();
        HashMap<String, String> result = new HashMap<>();


        try {
            if (user_id.isEmpty()) {
                throw new EmptyValueExceptions("id is empty");
            }
            if (user_password.isEmpty()) {
                throw new EmptyValueExceptions("password is empty");
            }
            if (user_nickname.isEmpty()) {
                throw new EmptyValueExceptions("nickname is empty");
            }
            if (user_email.isEmpty()) {
                throw new EmptyValueExceptions("email is empty");
            }
            if (user_name.isEmpty()) {
                throw new EmptyValueExceptions("name is empty");
            }
        } catch (EmptyValueExceptions e) {
            result.put("result", "error");
            result.put("message", e.getMessage());
            return result;
        }

        try {
            if (!user_password.equals(user_confirm_password)) {
                throw new PasswordIsInvalid("password is not confirmed");
            }
        } catch (PasswordIsInvalid e) {
            result.put("result", "error");
            result.put("message", e.getMessage());
            return result;
        }

        String securePassword = PasswordUtils.generateSecurePassword(user_password, this.token);

        this.userRepository.save(
                new Users(
                        user_id,
                        securePassword,
                        user_nickname,
                        user_email,
                        user_name
                )
        );
        result.put("result", "success");
        result.put("message", "회원가입이 완료되었습니다.");
        return result;
    }
}
