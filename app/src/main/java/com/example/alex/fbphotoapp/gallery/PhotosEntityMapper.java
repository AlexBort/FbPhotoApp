package com.example.alex.fbphotoapp.gallery;

import com.example.alex.fbphotoapp.data.api.RemoteDataSource;
import com.example.alex.fbphotoapp.data.model.dao.PhotoEntity;
import com.example.alex.fbphotoapp.data.model.dto.PageModel;
import com.example.alex.fbphotoapp.data.model.dto.Photo;

import java.util.ArrayList;
import java.util.List;

import static com.example.alex.fbphotoapp.data.storage.SessionSharedPreferences.get;

public class PhotosEntityMapper {

    public static List<PhotoEntity> apply(String key, PageModel<Photo> photoPageModel) {
        List<PhotoEntity> entityList = new ArrayList<>();
        for (Photo photo : photoPageModel.getCollection()) {
            PhotoEntity entity = new PhotoEntity();
            entity.setUrl(RemoteDataSource.BASE_URL + photo.getId() + "/picture?type=normal&access_token=" + get().getToken());
            entity.setAlbumId(key);
            entityList.add(entity);
        }
        return entityList;
    }
}
