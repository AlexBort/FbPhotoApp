package com.example.alex.fbphotoapp.api;

import com.example.alex.fbphotoapp.model.AlbumResponse;
import com.example.alex.fbphotoapp.model.PhotoResponse;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IRequestsApi {

    @GET("{userId}/albums")
    @NotNull
    Call<AlbumResponse> getAlbums(@Path("userId") @NotNull String var1,
                                  @Query("access_token")
    @NotNull String var2,
                                  @Query("fields") @NotNull String var3);

    @GET("{userId}/photos")
    @NotNull
    Call<PhotoResponse> getPhotos(@Path("userId") @NotNull String var1,
                                  @Query("access_token") @NotNull String var2,
                                  @Query("fields") @NotNull String var3,
                                  @Query("type") @NotNull String var4);

}
