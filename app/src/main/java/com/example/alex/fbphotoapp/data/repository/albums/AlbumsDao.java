package com.example.alex.fbphotoapp.data.repository.albums;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.alex.fbphotoapp.data.model.dao.AlbumEntity;
import com.example.alex.fbphotoapp.data.repository.BaseDao;

import java.util.List;


@Dao
public interface AlbumsDao extends BaseDao<AlbumEntity> {

    @Query("SELECT * from albums_table")
    LiveData<List<AlbumEntity>> getAllAlbums();

    @Query("DELETE FROM albums_table")
    void deleteAll();
}
