package com.example.alex.fbphotoapp.data.repository.photos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.alex.fbphotoapp.data.model.dao.PhotoEntity;
import com.example.alex.fbphotoapp.data.repository.BaseDao;

import java.util.List;

@Dao
public interface PhotosDao extends BaseDao<PhotoEntity> {

    @Query("SELECT * from photos_table WHERE albumId = :id")
    LiveData<List<PhotoEntity>> getAllPhotos(String id);

    @Query("DELETE FROM photos_table WHERE albumId = :id")
    void deleteAll(String id);
}
