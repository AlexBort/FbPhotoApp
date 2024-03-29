package com.example.alex.fbphotoapp.data.repository.albums;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.alex.fbphotoapp.data.model.dao.AlbumEntity;
import com.example.alex.fbphotoapp.data.repository.BaseRepository;

import java.util.List;


public class AlbumsRepository extends BaseRepository<AlbumEntity> {

    private AlbumsDao albumsDao;
    private LiveData<List<AlbumEntity>> allAlbums;

    public AlbumsRepository(Application application) {
        super(application);
        albumsDao = database.albumsDao();
        allAlbums = albumsDao.getAllAlbums();
    }

    @Override
    public void updateData(List<AlbumEntity> data, String key) {
        new InsertAsyncTask(albumsDao, data).execute();
    }

    public LiveData<List<AlbumEntity>> getAllAlbums() {
        return allAlbums;
    }

    private static class InsertAsyncTask extends AsyncTask<Void, Void, List<AlbumEntity>> {

        private AlbumsDao dao;
        private List<AlbumEntity> data;

        public InsertAsyncTask(AlbumsDao dao, List<AlbumEntity> data) {
            this.dao = dao;
            this.data = data;
        }

        @Override
        protected List<AlbumEntity> doInBackground(Void... voids) {
            dao.deleteAll();
            dao.insert(data);
            return null;
        }
    }
}
