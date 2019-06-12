package com.example.alex.fbphotoapp.gallery;


import android.arch.lifecycle.LiveData;

import com.example.alex.fbphotoapp.App;
import com.example.alex.fbphotoapp.BaseViewModel;
import com.example.alex.fbphotoapp.data.model.dao.PhotoEntity;
import com.example.alex.fbphotoapp.data.model.dto.PageModel;
import com.example.alex.fbphotoapp.data.model.dto.Photo;
import com.example.alex.fbphotoapp.data.repository.photos.PhotosRepository;
import com.example.alex.fbphotoapp.utils.Utils;

import java.util.List;

import static com.example.alex.fbphotoapp.data.api.RemoteDataSource.get;

public class DetailAlbumsViewModel extends BaseViewModel {

    private PhotosRepository repository;
    private LiveData<List<PhotoEntity>> allPhotos;

    public DetailAlbumsViewModel(String key) {
        repository = new PhotosRepository(App.getInstance(), key);
        allPhotos = repository.getAllPhotos();
    }

    public LiveData<List<PhotoEntity>> getAlbumPhotosCallback() {
        return allPhotos;
    }

    public void getAlbumPhotosRequest(final String id) {

        if (!Utils.isNetworkAvailable(App.getInstance())) {
            isRefreshing.set(false);
            return;
        }

        get().getAlbumsPhotos(id)
                .subscribe(new BaseSingleObserver<PageModel<Photo>>() {
                    @Override
                    public void onSuccess(PageModel<Photo> data) {
                        super.onSuccess(data);
                        if (data.getCollection().size() != 0) {
                            List<PhotoEntity> entities = PhotosEntityMapper.apply(id, data);
                            repository.updateData(entities, id);
                        }
                    }
                });
    }
}
