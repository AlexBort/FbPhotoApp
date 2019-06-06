package com.example.alex.fbphotoapp.mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements IBaseView {

    protected T presenter;

    protected abstract T createPresenter();

    protected abstract int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        setContentView(getLayoutId());
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onUnbindView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onBindView(this);
    }
}
