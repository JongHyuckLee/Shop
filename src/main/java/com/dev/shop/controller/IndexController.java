package com.dev.shop.controller;

import com.dev.shop.mongo.Products;
import com.dev.shop.mongo.Users;
import com.dev.shop.repository.ProductRepository;
import com.dev.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private ProductRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model, HttpSession session, HttpServletResponse response) {
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

        int data_cnt = (int)this.repository.count();
        if (data_cnt == 0) {
            this.repository.save(getData());
        }

        return "index";
    }

    private ArrayList<Products> getData() {
        ArrayList<Products> products = new ArrayList<>();
        products.add(
           new Products(
                   "케이블바이트-어피치",
                   "9000원",
                   "https://img1.daumcdn.net/thumb/R300x300/?fname=https%3A%2F%2Ft1.daumcdn.net%2Ffriends%2Fprod%2Fproduct%2FFRPBAPCBT8100_AW_00.jpg"
           )
        );
        products.add(
                new Products(
                        "케이블바이트-라이언",
                        "9000원",
                        "https://img1.daumcdn.net/thumb/R300x300/?fname=https%3A%2F%2Ft1.daumcdn.net%2Ffriends%2Fprod%2Fproduct%2FFRPBRYCBT8100_AW_00.jpg"
                )
        );
        products.add(
                new Products(
                        "LED조명-라이언",
                        "29000원",
                        "https://img1.daumcdn.net/thumb/R300x300/?fname=https%3A%2F%2Ft1.daumcdn.net%2Ffriends%2Fprod%2Fproduct%2FFRPBFRFLD8204_AW_00.jpg"
                )
        );
        products.add(
                new Products(
                        "프렌즈랜드 캐슬 브릭",
                        "49000원",
                        "https://img1.daumcdn.net/thumb/R300x300/?fname=https%3A%2F%2Ft1.daumcdn.net%2Ffriends%2Fprod%2Fproduct%2FFRPBRYFLC0001_AW_00.jpg"
                )
        );
        products.add(
                new Products(
                        "프렌즈랜드 미니인형_라이언",
                        "12000원",
                        "https://img1.daumcdn.net/thumb/R300x300/?fname=https%3A%2F%2Ft1.daumcdn.net%2Ffriends%2Fprod%2Fproduct%2FFRPBRYFLD0001_AW_00.jpg"
                )
        );
        products.add(
                new Products(
                        "프렌즈랜드 미니인형_어피치",
                        "12000원",
                        "https://img1.daumcdn.net/thumb/R300x300/?fname=https%3A%2F%2Ft1.daumcdn.net%2Ffriends%2Fprod%2Fproduct%2FFRPBAPFLD0001_AW_00.jpg"
                )
        );
        products.add(
                new Products(
                        "프렌즈랜드 미니인형_무지",
                        "12000원",
                        "https://img1.daumcdn.net/thumb/R300x300/?fname=https%3A%2F%2Ft1.daumcdn.net%2Ffriends%2Fprod%2Fproduct%2FFRPBMZFLD0001_AW_00.jpg"
                )
        );
        products.add(
                new Products(
                        "프렌즈랜드 미니인형_라이언",
                        "12000원",
                        "https://img1.daumcdn.net/thumb/R300x300/?fname=https%3A%2F%2Ft1.daumcdn.net%2Ffriends%2Fprod%2Fproduct%2FFRPBRYFLB0003_AW_00.jpg"
                )
        );
        products.add(
                new Products(
                        "프렌즈랜드 봉제팔찌_어피치",
                        "5500원",
                        "https://img1.daumcdn.net/thumb/R300x300/?fname=https%3A%2F%2Ft1.daumcdn.net%2Ffriends%2Fprod%2Fproduct%2FFRPBAPFLB0003_AW_00.jpg"
                )
        );
        products.add(
                new Products(
                        "프렌즈랜드 봉제팔찌_무지",
                        "5500원",
                        "https://img1.daumcdn.net/thumb/R300x300/?fname=https%3A%2F%2Ft1.daumcdn.net%2Ffriends%2Fprod%2Fproduct%2FFRPBMZFLB0003_AW_00.jpg"
                )
        );
        return products;
    }
}
