package com.example.alex.fbphotoapp.mvp.album;

import com.example.alex.fbphotoapp.model.Album;
import com.example.alex.fbphotoapp.model.AlbumResponse;
import com.example.alex.fbphotoapp.mvp.base.BasePresenter;
import com.example.alex.fbphotoapp.new_api.RequestManager;
import com.example.alex.fbphotoapp.new_api.ResponseListener;
import com.facebook.AccessToken;

import java.util.ArrayList;
import java.util.Iterator;

public class AlbumPresenter extends BasePresenter<IAlbumView> {

    private RequestManager requestManager;


    public AlbumPresenter() {
        this.requestManager = new RequestManager();
    }

    public void responseAlbums(AlbumResponse response) {

    }


    private void requestAlbum(final IAlbumView view) {
//        val albumsList = dao.allAlbums
//        mViewMvp?.fillView(albumsList as ArrayList<Album>)
        requestManager.getAlbums(AccessToken.getCurrentAccessToken(), new ResponseListener<AlbumResponse>() {
            @Override
            public void successResponse(AlbumResponse response) {
                if (response != null) {
                    if (response.getData() != null) {
                        ArrayList<AlbumResponse.Data> list = response.getData();
                        for (AlbumResponse.Data data : list) {
                            Album album = new Album();
                            album = album.convertToAlbum(data);
                            view.updateAlbum(album);
                        }
                    }


//                    ArrayList list = response.getData();
//                    for (Object o : list) {
//
//                    }


//                    if (response != null) {
//                        ArrayList var10000 = response.getData();
//                        if (var10000 != null) {
//                            ArrayList var2 = var10000;
//                            int var4 = false;
//                            Iterable $receiver$iv = (Iterable)var2;
//                            Iterator var6 = $receiver$iv.iterator();
//
//                            while(var6.hasNext()) {
//                                Object element$iv = var6.next();
//                                Data it = (Data)element$iv;
//                                int var9 = false;
//                                Album album = (new Album()).convertToAlbum(it);
//                                if (!this.isAlbumExist(album)) {
//                                    this.dao.saveAlbum(album);
//                                    AlbumsView var12 = this.mViewMvp;
//                                    if (this.mViewMvp != null) {
//                                        var12.updateView(album);
//                                    }
//                                }
//                            }
//                        }
//                    }


                }


            }

            @Override
            public void failureResponse() {

            }
        });
    }

    @Override
    public void onBindView(IAlbumView view) {
        super.onBindView(view);
        requestAlbum(view);
    }
}
