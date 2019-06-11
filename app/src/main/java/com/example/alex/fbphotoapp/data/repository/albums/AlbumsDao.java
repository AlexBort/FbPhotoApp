package winstars.winstars.facebookphoto.data.repository.albums;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import winstars.winstars.facebookphoto.data.entity.dao.AlbumEntity;
import winstars.winstars.facebookphoto.data.repository.BaseDao;

@Dao
public interface AlbumsDao extends BaseDao<AlbumEntity> {

    @Query("SELECT * from albums_table")
    LiveData<List<AlbumEntity>> getAllAlbums();

    @Query("DELETE FROM albums_table")
    void deleteAll();
}
