package com.dev.shop.exceptions;

/**
 * AlreadyPurchased - 동일 상품을 이미 구입했을 경우의 exception
 */
public class AlreadyPurchased extends Exception {
    public AlreadyPurchased(String msg) {
        super(msg);
    }
}//end class
