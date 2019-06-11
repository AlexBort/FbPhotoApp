package com.example.alex.fbphotoapp.album;

import com.example.alex.fbphotoapp.data.api.RemoteDataSource;
import com.example.alex.fbphotoapp.data.model.dao.AlbumEntity;
import com.example.alex.fbphotoapp.data.model.dto.Album;
import com.example.alex.fbphotoapp.data.model.dto.PageModel;
import com.example.alex.fbphotoapp.data.storage.SessionSharedPreferences;
import com.example.alex.fbphotoapp.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class AlbumEntityMapper {


    public static List<AlbumEntity> apply(PageModel<Album> albumPageModel) {
        List<AlbumEntity> entityList = new ArrayList<>();
        for (Album album : albumPageModel.getCollection()) {
            AlbumEntity entity = new AlbumEntity();
            entity.setId(album.getId());
            entity.setUrl(RemoteDataSource.BASE_URL + album.getId() + "/picture?access_token=" + SessionSharedPreferences.get().getToken());
            entity.setDate(Utils.parseDate(album.getDate()));
            entity.setName(album.getName());
            entityList.add(entity);
        }
        return entityList;
    }

}
