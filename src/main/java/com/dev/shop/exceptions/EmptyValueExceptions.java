package com.dev.shop.exceptions;

/**
 * EmptyValueExceptions - 조회 데이터가 빈데이터일 때 발생하는 exeption
 */
public class EmptyValueExceptions extends Exception {
    public EmptyValueExceptions(String msg) {
        super(msg);
    }
}//end class
