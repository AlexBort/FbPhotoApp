package com.example.alex.fbphotoapp.data.api;

import com.example.alex.fbphotoapp.data.model.dto.Album;
import com.example.alex.fbphotoapp.data.model.dto.PageModel;
import com.example.alex.fbphotoapp.data.model.dto.Photo;
import com.example.alex.fbphotoapp.data.storage.SessionSharedPreferences;

import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource {

    private static RemoteDataSource instance;
    private PhotoService service;
    public static final String BASE_URL = "https://graph.facebook.com/v3.2/";

    private RemoteDataSource() {
        Retrofit retrofit = createRetrofit(createOkHttpClientBuilder());
        service = retrofit.create(PhotoService.class);
    }

    public static RemoteDataSource get() {
        if (instance == null) {
            instance = new RemoteDataSource();
        }
        return instance;
    }

    private OkHttpClient.Builder createOkHttpClientBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(2, TimeUnit.MINUTES);
        builder.readTimeout(2, TimeUnit.MINUTES);
        builder.writeTimeout(2, TimeUnit.MINUTES);
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);
        return builder;
    }

    private Retrofit createRetrofit(OkHttpClient.Builder builder) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(builder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Single<PageModel<Photo>> getAlbumsPhotos(String id) {
        return service.getAlbumPhotos(id, SessionSharedPreferences.get().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<PageModel<Album>> getAlbums() {
        return service.getAlbums(SessionSharedPreferences.get().getUserId(), SessionSharedPreferences.get().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
