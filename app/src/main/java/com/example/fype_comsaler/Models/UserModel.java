package com.example.fype_comsaler.Models;

public class UserModel {
    String email, name, storeName, userName ;

    public UserModel() {
    }

    public UserModel(String email, String name,  String storeName, String userName) {
        this.email = email;
        this.name = name;
        this.storeName = storeName;
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
