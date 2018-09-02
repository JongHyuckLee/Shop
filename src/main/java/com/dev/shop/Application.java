package com.dev.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application - 웹 시작 점
 */
@SpringBootApplication
public class Application extends ServletInitializer {
    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }
}//end class
