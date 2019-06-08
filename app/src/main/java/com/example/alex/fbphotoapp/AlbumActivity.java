package com.example.alex.fbphotoapp;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.alex.fbphotoapp.model.Album;
import com.example.alex.fbphotoapp.mvp.base.BaseActivity;
import com.example.alex.fbphotoapp.mvp.album.AlbumPresenter;
import com.example.alex.fbphotoapp.mvp.album.IAlbumView;

import java.util.ArrayList;

public class AlbumActivity extends BaseActivity<AlbumPresenter> implements IAlbumView {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected AlbumPresenter createPresenter() {
        return new AlbumPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_album;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView = findViewById(R.id.recycler_view);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh);

        Toast.makeText(AlbumActivity.this, AlbumActivity.class.getSimpleName(), Toast.LENGTH_SHORT).show();

    }


    @Override
    public void showAlbums(ArrayList<Album> list) {

    }

    @Override
    public void updateAlbum(Album album) {

    }
}
