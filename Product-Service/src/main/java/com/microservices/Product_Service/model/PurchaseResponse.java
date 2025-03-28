package com.microservices.Product_Service.model;

public class PurchaseResponse {

    private Long userId;
    private String userName;
    private Long productId;
    private String productName;
    private double price;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }



    public PurchaseResponse(Long userId, String userName, Long productId, String productName, double price) {
        this.userId = userId;
        this.userName = userName;
        this.productId = productId;
        this.productName = productName;
        this.price=price;
    }
}
