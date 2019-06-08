package com.example.alex.fbphotoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.alex.fbphotoapp.mvp.base.BaseActivity;
import com.example.alex.fbphotoapp.mvp.photo.IPhotoView;
import com.example.alex.fbphotoapp.mvp.photo.PhotoPresenter;

public class PhotoActivity extends BaseActivity<PhotoPresenter> implements IPhotoView {


    @Override
    protected PhotoPresenter createPresenter() {
        return new PhotoPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_photo;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
    }
}
