package com.example.alex.fbphotoapp.mvp.album;

import com.example.alex.fbphotoapp.db.AlbumData;
import com.example.alex.fbphotoapp.db.DbHelper;
import com.example.alex.fbphotoapp.db.HelperFactory;
import com.example.alex.fbphotoapp.model.Album;
import com.example.alex.fbphotoapp.model.AlbumResponse;
import com.example.alex.fbphotoapp.mvp.base.BasePresenter;
import com.example.alex.fbphotoapp.api.RequestManager;
import com.example.alex.fbphotoapp.api.ResponseListener;
import com.facebook.AccessToken;

import java.util.ArrayList;
import java.util.List;

public class AlbumPresenter extends BasePresenter<IAlbumView> {

    private RequestManager requestManager;
    private ArrayList<Album> listAlbums = new ArrayList<>();
    private AlbumData albumData;


    public AlbumPresenter() {
        this.requestManager = new RequestManager();
        DbHelper helper = HelperFactory.getHelper();
        if (helper != null && helper.getAlbumData() != null) {
            albumData = helper.getAlbumData();
        }
    }

    public void responseAlbums(AlbumResponse response) {

    }


    private void getAlbums(IAlbumView view) {
        if (albumData != null) {
            List<Album> list = albumData.getAllAlbums();
            view.showAlbums(list);
        }
        requestAlbum(view);
    }


    private boolean isAlbumExist(Album album) {
        List<Album> list = albumData.getAllAlbums();
        for (Album model : list) {
            if (model.equals(album)) {
                return true;
            }
        }
        return false;
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
                            album = album.toAlbum(data);
                            if (!isAlbumExist(album)) {
                                albumData.saveAlbum(album);
                                view.updateAlbum(album);
                            }
                        }
//                        view.showAlbums(listAlbums);
                    }
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
        getAlbums(view);
    }
}
