package com.example.alex.fbphotoapp.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public final class FbGraphApi {

    private final String BASE_URL = "https://graph.facebook.com/v3.0/";

    public String getBASE_URL() {
        return BASE_URL;
    }

    public final FbGraphApi.GatewayApi getApi() {
        Gson gson = (new GsonBuilder()).setDateFormat("yyyy-MM-dd HH:mm:ss").setLenient().create();
        Retrofit retrofit =
                (new Retrofit.Builder()).baseUrl(this.BASE_URL).
                        addConverterFactory(GsonConverterFactory.
                                create(gson)).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        return retrofit.create(FbGraphApi.GatewayApi.class);
    }


    public interface GatewayApi {
        @GET("{userId}/albums")
        @NotNull
        Observable getAlbums(@Path("userId") @NotNull String var1, @Query("access_token") @NotNull String var2, @Query("fields") @NotNull String var3);

        @GET("{userId}/photos")
        @NotNull
        Observable getPhotos(@Path("userId") @NotNull String var1, @Query("access_token") @NotNull String var2, @Query("fields") @NotNull String var3, @Query("type") @NotNull String var4);
    }


}
