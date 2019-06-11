package com.example.alex.fbphotoapp.data.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PageModel<T> {

    @SerializedName("data")
    @Expose
    private List<T> collection;
    @SerializedName("paging")
    @Expose
    private PaginationModel pagination;

    public PageModel() {
        collection = new ArrayList<>();
    }

    public List<T> getCollection() {
        return collection;
    }

    public PaginationModel getPagination() {
        return pagination;
    }
}
