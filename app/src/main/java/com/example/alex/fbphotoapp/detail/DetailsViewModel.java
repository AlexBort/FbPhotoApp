package com.example.alex.fbphotoapp.detail;

import android.arch.lifecycle.LiveData;

import com.example.alex.fbphotoapp.App;
import com.example.alex.fbphotoapp.BaseViewModel;
import com.example.alex.fbphotoapp.data.model.dao.PhotoEntity;
import com.example.alex.fbphotoapp.data.repository.photos.PhotosRepository;

import java.util.List;

public class DetailsViewModel extends BaseViewModel {

    private LiveData<List<PhotoEntity>> allPhotos;

    public DetailsViewModel(String key) {
        PhotosRepository repository = new PhotosRepository(App.getInstance(), key);
        allPhotos = repository.getAllPhotos();
    }

    public LiveData<List<PhotoEntity>> getAllPhotos() {
        return allPhotos;
    }
}
