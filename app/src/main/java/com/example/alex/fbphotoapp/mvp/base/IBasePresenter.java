package com.example.alex.fbphotoapp.mvp.base;

interface IBasePresenter<V> {

    void onBindView(V view);

    void onUnbindView();

    void onBackButtonPressed(IBaseView view);

}
