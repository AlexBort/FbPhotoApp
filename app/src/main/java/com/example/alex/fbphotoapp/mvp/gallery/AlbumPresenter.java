package com.example.alex.fbphotoapp.mvp.gallery;

import com.example.alex.fbphotoapp.model.AlbumResponse;
import com.example.alex.fbphotoapp.mvp.base.BasePresenter;

public class AlbumPresenter extends BasePresenter<IAlbumView> {


    public void responseAlbums(AlbumResponse response) {

    }

    private void requestGallery() {
//        val albumsList = dao.allAlbums
//        mViewMvp?.fillView(albumsList as ArrayList<Album>)
    }

    @Override
    public void onBindView(IAlbumView view) {
        super.onBindView(view);
        requestGallery();
    }
}
