package com.example.alex.fbphotoapp.data.model.dto;


import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

public class Album  {

    @PrimaryKey
    @SerializedName("id")
    private String id;

    @SerializedName("created_time")
    private String date;

    @SerializedName("name")
    private String name;

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public Album() {

    }
}
