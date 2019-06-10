package com.example.alex.fbphotoapp.db;

import com.example.alex.fbphotoapp.model.Photo;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

public class PhotoData extends BaseDaoImpl<Photo, Integer> {


    PhotoData(ConnectionSource connectionSource, Class<Photo> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<Photo> getAllPhotos() {
        try {
            return this.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void savePhoto(Photo obj) {
        try {
            this.createIfNotExists(obj);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Photo> findPhotoByAlbumName(String albumId) {
        QueryBuilder<Photo, Integer> queryBuilder = this.queryBuilder();
        try {
            queryBuilder.where().eq("albumId", albumId);
            return queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
