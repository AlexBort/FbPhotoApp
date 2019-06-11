package com.example.alex.fbphotoapp.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.alex.fbphotoapp.BaseActivity;
import com.example.alex.fbphotoapp.R;
import com.example.alex.fbphotoapp.data.storage.SessionSharedPreferences;
import com.example.alex.fbphotoapp.databinding.ActivityLoginBinding;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;

public class LoginActivity extends BaseActivity implements FacebookCallback<LoginResult> {

    private CallbackManager callbackManager;
    private ActivityLoginBinding binding;

    @Override
    protected void onStart() {
        super.onStart();
        if (SessionSharedPreferences.get().isActive()) {
            AlbumsActivity.launch(this, false);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        callbackManager = CallbackManager.Factory.create();
        observeFacebookPermission();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void observeFacebookPermission() {
        binding.loginButton.setReadPermissions("user_photos");
        binding.loginButton.registerCallback(callbackManager, this);
    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        SessionSharedPreferences.get().startSession(loginResult.getAccessToken().getToken(), loginResult.getAccessToken().getUserId());
        AlbumsActivity.launch(this, true);
    }

    @Override
    public void onCancel() {
        Toast.makeText(this, getString(R.string.title_toast_cancel), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(FacebookException error) {
        Toast.makeText(this, getString(R.string.title_toast_something_went_wrong), Toast.LENGTH_SHORT).show();
    }
}
