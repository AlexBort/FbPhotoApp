package com.example.alex.fbphotoapp.mvp.photo;

import com.example.alex.fbphotoapp.api.RequestManager;
import com.example.alex.fbphotoapp.api.ResponseListener;
import com.example.alex.fbphotoapp.db.AlbumData;
import com.example.alex.fbphotoapp.db.HelperFactory;
import com.example.alex.fbphotoapp.db.PhotoData;
import com.example.alex.fbphotoapp.model.Album;
import com.example.alex.fbphotoapp.model.IResponse;
import com.example.alex.fbphotoapp.model.Photo;
import com.example.alex.fbphotoapp.model.PhotoResponse;
import com.example.alex.fbphotoapp.mvp.base.BasePresenter;
import com.facebook.AccessToken;

import java.util.ArrayList;
import java.util.List;

public class PhotoPresenter extends BasePresenter<IPhotoView> {

    private String id;
    private RequestManager requestManager;
    private ArrayList<Photo> listPhotos = new ArrayList<>();
    private PhotoData photoData = HelperFactory.getHelper().getPhotoData();


    public PhotoPresenter(String id) {
        this.id = id;

    }


    private void getPhotos(IPhotoView view) {
        List<String> listUrl = new ArrayList<>();
        if (!id.isEmpty()) {
            List<Photo> photoList = photoData.findPhotoByAlbumName(id);
            for (Photo photo : photoList) {
                if (photo.getUrl() != null) {
                    listUrl.add(photo.getUrl());
                }
            }
            view.showPhotos((ArrayList<String>) listUrl);
        }
        requestPhotos(view);
    }

    private boolean isExistPhoto(Photo photo) {
        List<Photo> list = photoData.getAllPhotos();
        for (Photo model : list) {
            if (model.equals(photo)) {
                return true;
            }
        }
        return false;
    }


    private void requestPhotos(final IPhotoView view) {
        requestManager.getPhotos(AccessToken.getCurrentAccessToken(), new ResponseListener<PhotoResponse>() {
            @Override
            public void successResponse(PhotoResponse response) {
                ArrayList<PhotoResponse.Data> list = response.getData();
                for (PhotoResponse.Data data : list) {
                    Photo photo = new Photo();
                    photo = photo.toPhoto(data);
                    if (!isExistPhoto(photo)) {
                        photoData.savePhoto(photo);
                        if (photo.getAlbumId().equals(id)) {
                            view.updatePhoto(photo);
                        }
                    }
                } }
            @Override
            public void failureResponse() {

            }
        });
    }

    @Override
    public void onBindView(IPhotoView view) {
        super.onBindView(view);
        getPhotos(view);
    }
}
