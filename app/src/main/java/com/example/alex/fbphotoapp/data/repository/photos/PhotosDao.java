package winstars.winstars.facebookphoto.data.repository.photos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import winstars.winstars.facebookphoto.data.entity.dao.PhotoEntity;
import winstars.winstars.facebookphoto.data.repository.BaseDao;

@Dao
public interface PhotosDao extends BaseDao<PhotoEntity> {

    @Query("SELECT * from photos_table WHERE albumId = :id")
    LiveData<List<PhotoEntity>> getAllPhotos(String id);

    @Query("DELETE FROM photos_table WHERE albumId = :id")
    void deleteAll(String id);
}
