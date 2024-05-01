package com.example.fype_comsaler.Models;

public class FirebaseOrderModel {
//    String cartId;
//    boolean is_slected;
//    String orderNo, ProductImg,PName,PPrice,PQty,salerUid;


    String courier,customerAdress,cCityN,cN,cNum,dCharges,price,orderNum,orderPlacingDate,orderStatus;

    public FirebaseOrderModel() {
    }

    public FirebaseOrderModel(String courier, String customerAdress, String cCityN, String cN, String cNum, String dCharges, String price, String orderNum, String orderPlacingDate, String orderStatus) {
        this.courier = courier;
        this.customerAdress = customerAdress;
        this.cCityN = cCityN;
        this.cN = cN;
        this.cNum = cNum;
        this.dCharges = dCharges;
        this.price = price;
        this.orderNum = orderNum;
        this.orderPlacingDate = orderPlacingDate;
        this.orderStatus = orderStatus;
    }

    public String getCourier() {
        return courier;
    }

    public void setCourier(String courier) {
        this.courier = courier;
    }

    public String getCustomerAdress() {
        return customerAdress;
    }

    public void setCustomerAdress(String customerAdress) {
        this.customerAdress = customerAdress;
    }

    public String getcCityN() {
        return cCityN;
    }

    public void setcCityN(String cCityN) {
        this.cCityN = cCityN;
    }

    public String getcN() {
        return cN;
    }

    public void setcN(String cN) {
        this.cN = cN;
    }

    public String getcNum() {
        return cNum;
    }

    public void setcNum(String cNum) {
        this.cNum = cNum;
    }

    public String getdCharges() {
        return dCharges;
    }

    public void setdCharges(String dCharges) {
        this.dCharges = dCharges;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getOrderPlacingDate() {
        return orderPlacingDate;
    }

    public void setOrderPlacingDate(String orderPlacingDate) {
        this.orderPlacingDate = orderPlacingDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
