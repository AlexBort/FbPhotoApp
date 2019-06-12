package com.example.alex.fbphotoapp.album;


import android.arch.lifecycle.LiveData;

import com.example.alex.fbphotoapp.App;
import com.example.alex.fbphotoapp.BaseViewModel;
import com.example.alex.fbphotoapp.data.model.dao.AlbumEntity;
import com.example.alex.fbphotoapp.data.model.dto.Album;
import com.example.alex.fbphotoapp.data.model.dto.PageModel;
import com.example.alex.fbphotoapp.data.repository.albums.AlbumsRepository;
import com.example.alex.fbphotoapp.utils.Utils;

import java.util.List;

import static com.example.alex.fbphotoapp.data.api.RemoteDataSource.get;

public class AlbumsViewModel extends BaseViewModel {

    private AlbumsRepository repository;
    private LiveData<List<AlbumEntity>> allAlbums;

    public AlbumsViewModel() {
        repository = new AlbumsRepository(App.getInstance());
        allAlbums = repository.getAllAlbums();
    }

    public LiveData<List<AlbumEntity>> getAllAlbumsCallback() {
        return allAlbums;
    }

    public void getAlbumsRequest() {

        if (!Utils.isNetworkAvailable(App.getInstance())) {
            isRefreshing.set(false);
            return;
        }

        get().getAlbums()
                .subscribe(new BaseSingleObserver<PageModel<Album>>() {
                    @Override
                    public void onSuccess(PageModel<Album> data) {
                        super.onSuccess(data);
                        if (data.getCollection().size() != 0) {
                            List<AlbumEntity> entities = AlbumEntityMapper.apply(data);
                            repository.updateData(entities, null);
                        }
                    }
                });
    }
}
