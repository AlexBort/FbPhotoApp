package com.example.alex.fbphotoapp;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.alex.fbphotoapp.mvp.base.BaseActivity;
import com.example.alex.fbphotoapp.mvp.photo.IPhotoView;
import com.example.alex.fbphotoapp.mvp.photo.PhotoPresenter;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;

public class PhotosActivity extends BaseActivity<PhotoPresenter> implements IPhotoView {


    private RecyclerView recyclerView;
    private BlurView blurView;

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

        recyclerView = findViewById(R.id.photos_recycler);
        blurView = findViewById(R.id.blur_view);

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void blur() {
        float radius = 0.01f;

        Window decorView = this.getWindow();
        ViewGroup rootView = decorView.findViewById(android.R.id.content);
        Drawable windowBackground = rootView.getBackground();

        blurView.setupWith(rootView)
                .windowBackground(windowBackground)
                .blurAlgorithm(new RenderScriptBlur(this))
                .blurRadius(radius)
                .setHasFixedTransformationMatrix(true);

        blurView.setBlurEnabled(false);
    }

}
