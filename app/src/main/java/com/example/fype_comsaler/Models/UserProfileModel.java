package com.example.fype_comsaler.Models;

public class UserProfileModel {

    String email, name, password, storeName, userId, username ;

    public UserProfileModel() {
    }

    public UserProfileModel(String email, String name, String password, String storeName, String userId,
                            String username) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.storeName = storeName;
        this.username =username;
        this.userId = userId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
