package winstars.winstars.facebookphoto.data.repository;

import android.app.Application;

import java.util.List;

import winstars.winstars.facebookphoto.data.repository.db.PhotosDatabase;

public abstract class BaseRepository<T> {

    protected PhotosDatabase database;

    public BaseRepository(Application application) {
        database = PhotosDatabase.getDatabase(application);
    }

    protected abstract void updateData(List<T> data, String key);
}
