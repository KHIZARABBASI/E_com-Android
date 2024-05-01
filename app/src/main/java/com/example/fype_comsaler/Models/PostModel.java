package com.example.fype_comsaler.Models;

import android.net.Uri;
import android.widget.ImageView;

import java.io.Serializable;

public class PostModel implements Serializable {
    String img;
    String name, description, price;

    public PostModel() {
    }

    public PostModel(String description,String img, String name,  String price) {
        this.img = img;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
