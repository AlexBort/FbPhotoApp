package com.example.alex.fbphotoapp.db;

import com.example.alex.fbphotoapp.model.Album;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

public class AlbumData extends BaseDaoImpl<Album, Integer> {

    AlbumData(ConnectionSource connectionSource, Class<Album> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<Album> getAllAlbums() throws SQLException {
        return this.queryForAll();
    }

    public void saveAlbum(Album obj) {
        try {
            this.createIfNotExists(obj);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
