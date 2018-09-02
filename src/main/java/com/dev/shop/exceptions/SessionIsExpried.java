package com.dev.shop.exceptions;

/**
 * SessionIsExpired - 세션 만료시 발생하는 exception
 */
public class SessionIsExpried extends Exception {
    public SessionIsExpried(String msg) {
        super(msg);
    }
}//end class
