package com.example.alex.fbphotoapp.data.repository.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.alex.fbphotoapp.data.model.dao.AlbumEntity;
import com.example.alex.fbphotoapp.data.model.dao.PhotoEntity;
import com.example.alex.fbphotoapp.data.repository.albums.AlbumsDao;
import com.example.alex.fbphotoapp.data.repository.photos.PhotosDao;


@Database(entities = {PhotoEntity.class, AlbumEntity.class}, version = 1, exportSchema = false)
public abstract class PhotosDatabase extends RoomDatabase {

    private static volatile PhotosDatabase instance;

    public static PhotosDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (PhotosDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            PhotosDatabase.class, "photos_database")
                            .build();
                }
            }
        }
        return instance;
    }

    public abstract PhotosDao photosDao();

    public abstract AlbumsDao albumsDao();
}
