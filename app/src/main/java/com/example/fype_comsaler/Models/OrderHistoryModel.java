package com.example.fype_comsaler.Models;

public class OrderHistoryModel {

    String cartId,orderNo,productImage,productName,productPrice,productQty;


    public OrderHistoryModel() {
    }

    public OrderHistoryModel(String cartId, String orderNo, String productImage, String productName, String productPrice, String productQty) {
        this.cartId = cartId;
        this.orderNo = orderNo;
        this.productImage = productImage;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQty = productQty;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductQty() {
        return productQty;
    }

    public void setProductQty(String productQty) {
        this.productQty = productQty;
    }
}
