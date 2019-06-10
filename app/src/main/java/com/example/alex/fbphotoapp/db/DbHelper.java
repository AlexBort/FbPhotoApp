package com.example.alex.fbphotoapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.alex.fbphotoapp.model.Album;
import com.example.alex.fbphotoapp.model.Photo;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DbHelper extends OrmLiteSqliteOpenHelper {


    private AlbumData albumData = null;
    private PhotoData photoData = null;
    private static final String TAG = DbHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "fb_gallery.db";
    private static final int DATABASE_VERSION = 1;


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Album.class);
            TableUtils.createTable(connectionSource, Photo.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Album.class, true);
            TableUtils.dropTable(connectionSource, Photo.class, true);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        onCreate(database, connectionSource);
        Log.e(TAG, "error onUpgrade" + DATABASE_NAME + "from ver " + oldVersion);
    }

    public AlbumData getAlbumData() {
        if (albumData == null) {
            try {
                albumData = new AlbumData(getConnectionSource(), Album.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return albumData;
    }

    public PhotoData getPhotoData()  {
        if (photoData == null) {
            try {
                photoData = new PhotoData(getConnectionSource(), Photo.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return photoData;
    }

    @Override
    public void close() {
        super.close();
        albumData = null;
    }

}
