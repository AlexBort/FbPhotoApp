package com.example.alex.fbphotoapp.mvp.gallery;

import com.example.alex.fbphotoapp.api.RestManager;
import com.example.alex.fbphotoapp.model.AlbumResponse;
import com.example.alex.fbphotoapp.mvp.base.BasePresenter;
import com.facebook.AccessToken;

public class AlbumPresenter extends BasePresenter<IAlbumView> {

    private RestManager restManager;


    public AlbumPresenter() {
        this.restManager = new RestManager();
    }

    public void responseAlbums(AlbumResponse response) {

    }

    private void requestGallery() {
//        val albumsList = dao.allAlbums
//        mViewMvp?.fillView(albumsList as ArrayList<Album>)
        restManager.getAlbums(AccessToken.getCurrentAccessToken(), this);
    }

    @Override
    public void onBindView(IAlbumView view) {
        super.onBindView(view);
        requestGallery();
    }
}
