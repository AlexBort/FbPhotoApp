package com.example.alex.fbphotoapp.data.repository;

import android.app.Application;

import com.example.alex.fbphotoapp.data.repository.db.PhotosDatabase;

import java.util.List;


public abstract class BaseRepository<T> {

    protected PhotosDatabase database;

    public BaseRepository(Application application) {
        database = PhotosDatabase.getDatabase(application);
    }

    protected abstract void updateData(List<T> data, String key);
}
