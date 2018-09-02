package com.dev.shop.exceptions;

/**
 * PasswordIsInvalid - 비밀번호가 틀렸을 경우 발생하는 exception
 */
public class PasswordIsInvalid extends Exception {
    public PasswordIsInvalid(String msg) {
        super(msg);
    }
}//end class
