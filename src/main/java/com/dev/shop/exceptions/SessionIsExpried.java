package com.dev.shop.exceptions;

public class SessionIsExpried extends Exception {
    public SessionIsExpried(String msg) {
        super(msg);
    }
}
