package com.example.alex.fbphotoapp;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.alex.fbphotoapp.mvp.GalleryContract;
import com.example.alex.fbphotoapp.mvp.GalleryPresenterImpl;

public class GalleryActivity extends AppCompatActivity implements GalleryContract.GalleryView {

    private RecyclerView recyclerView;
    private GalleryContract.GalleryPresenter presenter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        recyclerView = findViewById(R.id.recycler_view);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        

        presenter = new GalleryPresenterImpl(this);

    }

    @Override
    public void showGallery() {

    }

    @Override
    public void updateGallery() {

    }
}
