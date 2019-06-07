package com.example.alex.fbphotoapp.api;

import android.util.Log;

import com.example.alex.fbphotoapp.model.AlbumResponse;
import com.example.alex.fbphotoapp.mvp.base.BasePresenter;
import com.example.alex.fbphotoapp.mvp.gallery.AlbumPresenter;
import com.facebook.AccessToken;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RestManager {


    private FbGraphApi graphApi;

    public RestManager() {
        this.graphApi = new FbGraphApi();
    }

    @SuppressWarnings("unchecked")
    public final void getAlbums(AccessToken token, final AlbumPresenter presenter) {
        this.graphApi.getApi().
                getAlbums(token.getUserId(), token.getToken(), "picture,name,created_time").
                subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Subscriber() {
                    @Override
                    public void onCompleted() {
                        Log.i("onCompleted: ", " completed!");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("error", e.getMessage());
                    }

                    @Override
                    public void onNext(Object o) {
                        AlbumResponse response = (AlbumResponse) o;
                        presenter.responseAlbums(response);
                    }
                });

    }

    public final void getPhoto(AccessToken token, BasePresenter<com.example.alex.fbphotoapp.mvp.base.IBaseView> presenter) {


    }

}
