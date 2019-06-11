package com.example.alex.fbphotoapp.data.repository.photos;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.alex.fbphotoapp.data.model.dao.PhotoEntity;
import com.example.alex.fbphotoapp.data.repository.BaseRepository;

import java.util.List;

public class PhotosRepository extends BaseRepository<PhotoEntity> {

    private PhotosDao photosDao;
    private LiveData<List<PhotoEntity>> allPhotos;

    public PhotosRepository(Application application, String key) {
        super(application);
        photosDao = database.photosDao();
        allPhotos = photosDao.getAllPhotos(key);
    }

    @Override
    public void updateData(List<PhotoEntity> data, String key) {
        new InsertAsyncTask(photosDao, data, key).execute();
    }

    public LiveData<List<PhotoEntity>> getAllPhotos() {
        return allPhotos;
    }

    private static class InsertAsyncTask extends AsyncTask<Void, Void, List<PhotoEntity>> {

        private PhotosDao dao;
        private List<PhotoEntity> data;
        private String key;

        public InsertAsyncTask(PhotosDao dao, List<PhotoEntity> data, String key) {
            this.dao = dao;
            this.data = data;
            this.key = key;
        }

        @Override
        protected List<PhotoEntity> doInBackground(Void... voids) {
            dao.deleteAll(key);
            dao.insert(data);
            return null;
        }
    }
}
