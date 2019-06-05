package com.example.alex.fbphotoapp;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Gallery;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private LoginButton loginButton;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_main);


        loginButton = findViewById(R.id.login_button);
        AccessToken accessToken = AccessToken.getCurrentAccessToken();

        if (checkFbToken(accessToken)) {
            loginFb();
        } else {
            openGalleryScreen();
            // TODO: 05.06.2019 !!
        }

    }

    private boolean checkFbToken(AccessToken token) {
        return token != null && !token.isExpired();
    }

    private void openGalleryScreen() {
        startActivity(new Intent(MainActivity.this, GalleryActivity.class));

    }

    private void loginFb() {
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email", "user_photos"));


        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                if (checkFbToken(loginResult.getAccessToken())) {
                    openGalleryScreen();
                }
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Log.e(MainActivity.class.getName(), "facebook error" + error.getMessage());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

    }
}
