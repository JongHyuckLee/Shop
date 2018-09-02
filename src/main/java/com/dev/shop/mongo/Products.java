package com.dev.shop.mongo;

import org.springframework.data.annotation.Id;

public class Products {

    @Id
    private String id;

    private String productName;
    private String productPrice;
    private String productImage;

    public String getId() {
        return id;
    }

    public String getProductImage() {
        return productImage;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public Products() {

    }
    public Products(String product_name, String product_price, String product_image) {
        this.productName = product_name;
        this.productPrice = product_price;
        this.productImage = product_image;
    }
}


