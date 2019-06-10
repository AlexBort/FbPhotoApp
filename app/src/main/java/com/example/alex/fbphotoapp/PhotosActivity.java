package com.example.alex.fbphotoapp;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;

import com.example.alex.fbphotoapp.adapters.PhotosAdapter;
import com.example.alex.fbphotoapp.model.Photo;
import com.example.alex.fbphotoapp.mvp.base.BaseActivity;
import com.example.alex.fbphotoapp.mvp.photo.IPhotoView;
import com.example.alex.fbphotoapp.mvp.photo.PhotoPresenter;

import java.util.ArrayList;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;


public class PhotosActivity extends BaseActivity<PhotoPresenter> implements IPhotoView {


    private RecyclerView recyclerView;
    private BlurView blurView;
    private PhotosAdapter adapter;
    private LinearLayout layout;

    @Override
    protected PhotoPresenter createPresenter() {

        return new PhotoPresenter(getIntent() != null && getIntent().getStringExtra("albumId") != null ?
                getIntent().getStringExtra("albumId") : "");
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
        layout = findViewById(R.id.linear);

    }

    private void initAdapter(ArrayList<String> list) {

        if (list != null && !list.isEmpty()) {
            adapter = new PhotosAdapter(list, layout);
            recyclerView.setLayoutManager(new GridLayoutManager(this,
                    3, GridLayoutManager.VERTICAL, false));
            recyclerView.setAdapter(adapter);
        }
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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void showPhotos(ArrayList<String> list) {
        initAdapter(list);
        blur();
    }

    @Override
    public void updatePhoto(Photo photo) {
        if (adapter != null && photo != null) {
            adapter.addToAdapter(photo);
        }
    }
}
