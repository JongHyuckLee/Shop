package com.dev.shop.controller;

import com.dev.shop.mongo.Products;
import com.dev.shop.repository.ProductRepository;
import com.dev.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * PageController Class : 화면 연결 메서드 모음
 * author by jonghyuck lee
 * date : 2018.09.02
 * method signup, products, purchased_list
 * sign up : 회원 가입 화면
 * products : 상품을 구입할 수 있는 화면
 * purchased_list : 회원 상품 목록을 볼 수 있는 화면
 */
@Controller
@RequestMapping("/page")
public class PageController {

    /**
     * productRepository 상품 몽고 db repository
     */
    @Autowired
    private ProductRepository productRepository;

    /**
     * userRepository 유저 몽고 db repository
     */
    @Autowired
    private UserRepository userRepository;

    /**
     *
     * @param model    view data
     * @param session  session data
     * @param response response object
     * description : 회원 가입 화면
     * @return String signup
     */
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model, HttpSession session, HttpServletResponse response) {
        if (session.getAttribute("user_session") != null) {
            response.setContentType("text/html; charset=UTF-8");
            try {
                PrintWriter out = response.getWriter();
                out.println("<script>alert('상품 화면으로 이동합니다.'); location.href='/page/products';</script>");
                out.flush();
            } catch (IOException e){
                return "redirect:/page/products";
            }
        }
        return "signup";
    }//end signup()

    /**
     * @param model    view data
     * @param session  session data
     * @param response response object
     * @return String products 상품 구입 화면
     */
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String products(Model model, HttpSession session, HttpServletResponse response) {
        if (session.getAttribute("user_session") == null) {
            response.setContentType("text/html; charset=UTF-8");
            try {
                PrintWriter out = response.getWriter();
                out.println("<script>alert('세션이 없습니다.'); location.href='/';</script>");
                out.flush();
            } catch (IOException e){
                return "redirect:/";
            }

        }
        String user_session = session.getAttribute("user_session").toString();
        byte[] decoded_bytes = Base64.getDecoder().decode(user_session);
        String decoded_session = new String(decoded_bytes);
        String user_id = decoded_session.split(":")[1];
        List<Products> products = productRepository.findAll();

        List<String> purchased = userRepository.findOne(user_id).getUserPurchase();

        model.addAttribute("products", products);
        model.addAttribute("_id", user_id);
        model.addAttribute("length", purchased.size());
        return "products";
    }//end products()

    /**
     * @param model    view data
     * @param session  session data
     * @param response response object
     * description : 구입한 상품 목록
     * @return String purchased_list
     */
    @RequestMapping(value = "/purchased_list", method = RequestMethod.GET)
    public String purchased_list(Model model, HttpSession session, HttpServletResponse response) {
        if (session.getAttribute("user_session") == null) {
            response.setContentType("text/html; charset=UTF-8");
            try {
                PrintWriter out = response.getWriter();
                out.println("<script>alert('세션이 없습니다.'); location.href='/';</script>");
                out.flush();
            } catch (IOException e){
                return "redirect:/";
            }

        }
        String user_session = session.getAttribute("user_session").toString();
        byte[] decoded_bytes = Base64.getDecoder().decode(user_session);
        String decoded_session = new String(decoded_bytes);
        String user_id = decoded_session.split(":")[1];
        List<String> products = userRepository.findOne(user_id).getUserPurchase();
        List<Products> purchased = (ArrayList<Products>)productRepository.findAll(products);
        int size = purchased.size();
        model.addAttribute("products", purchased);
        model.addAttribute("length", size);
        return "purchased_list";
    }//end purchased_list()
}//end class
