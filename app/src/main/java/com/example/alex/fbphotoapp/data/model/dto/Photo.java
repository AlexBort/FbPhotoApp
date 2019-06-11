package com.example.alex.fbphotoapp.data.model.dto;

import com.google.gson.annotations.SerializedName;

public class Photo  {

    @SerializedName("id")
    private String id;


    public String getId() {
        return id;
    }
}
