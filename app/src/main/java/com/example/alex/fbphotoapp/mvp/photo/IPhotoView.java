package com.example.alex.fbphotoapp.mvp.photo;

import com.example.alex.fbphotoapp.model.Photo;
import com.example.alex.fbphotoapp.mvp.base.IBaseView;

import java.util.ArrayList;

public interface IPhotoView extends IBaseView {

    void showPhotos(ArrayList<String> list);

    void updatePhoto(Photo photo);

}
