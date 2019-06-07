package com.example.alex.fbphotoapp.mvp.album;

import com.example.alex.fbphotoapp.model.AlbumResponse;
import com.example.alex.fbphotoapp.mvp.base.BasePresenter;
import com.example.alex.fbphotoapp.new_api.RequestManager;
import com.example.alex.fbphotoapp.new_api.ResponseListener;
import com.facebook.AccessToken;

public class AlbumPresenter extends BasePresenter<IAlbumView> {

    private RequestManager requestManager;


    public AlbumPresenter() {
        this.requestManager = new RequestManager();
    }

    public void responseAlbums(AlbumResponse response) {

    }

    private void requestAlbum() {
//        val albumsList = dao.allAlbums
//        mViewMvp?.fillView(albumsList as ArrayList<Album>)
        requestManager.getAlbums(AccessToken.getCurrentAccessToken(), new ResponseListener<AlbumResponse>() {
            @Override
            public void successResponse(AlbumResponse response) {
                response.getData();
            }

            @Override
            public void failureResponse() {

            }
        });
    }

    @Override
    public void onBindView(IAlbumView view) {
        super.onBindView(view);
        requestAlbum();
    }
}
