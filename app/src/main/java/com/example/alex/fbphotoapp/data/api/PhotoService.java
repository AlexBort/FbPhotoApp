package com.example.alex.fbphotoapp.data.api;

import com.example.alex.fbphotoapp.data.model.dto.Album;
import com.example.alex.fbphotoapp.data.model.dto.PageModel;
import com.example.alex.fbphotoapp.data.model.dto.Photo;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface PhotoService {

    @GET("{user-id}/albums")
    Single<PageModel<Album>> getAlbums(@Path("user-id") String id, @Query("access_token") String token);

    @GET("{album-id}/photos")
    Single<PageModel<Photo>> getAlbumPhotos(@Path("album-id") String id, @Query("access_token") String token);
}
