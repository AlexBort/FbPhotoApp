package com.example.alex.fbphotoapp.utils;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class DataBindingAdapters {


    @BindingAdapter("url")
    public static void setImageUrl(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(url)
                .into(view);
    }

}
