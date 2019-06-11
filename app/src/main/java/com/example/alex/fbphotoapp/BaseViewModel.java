package com.example.alex.fbphotoapp;

import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseViewModel extends ViewModel implements LifecycleObserver {

    public final CompositeDisposable compositeDisposable = new CompositeDisposable();
    public final ObservableBoolean isRefreshing = new ObservableBoolean();

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }

    public class BaseSingleObserver<T> implements SingleObserver<T> {

        @Override
        public void onSubscribe(Disposable disposable) {
            compositeDisposable.add(disposable);
            isRefreshing.set(false);
        }

        @Override
        public void onSuccess(T data) {
            isRefreshing.set(false);
        }

        @Override
        public void onError(Throwable throwable) {
            isRefreshing.set(false);
        }
    }
}
