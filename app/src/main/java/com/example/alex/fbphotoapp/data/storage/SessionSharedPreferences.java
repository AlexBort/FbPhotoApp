package com.example.alex.fbphotoapp.data.storage;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionSharedPreferences {

    public static final String KEY = SessionSharedPreferences.class.getCanonicalName();

    private SharedPreferences sharedPreferences;
    private static SessionSharedPreferences instance;

    public @interface SessionPrefKey {
        String TOKEN = "token";
        String USER_ID = "user_id";
    }

    private SessionSharedPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(KEY, Context.MODE_PRIVATE);
    }

    public static SessionSharedPreferences init(Context context) {
        if (instance == null) {
            instance = new SessionSharedPreferences(context);
        }
        return instance;
    }

    public static SessionSharedPreferences get() {
        return instance;
    }

    public void startSession(String token, String userId) {
        sharedPreferences.edit()
                .putString(SessionPrefKey.TOKEN, token)
                .putString(SessionPrefKey.USER_ID, userId)
                .apply();
    }

    public void stopSession() {
        sharedPreferences.edit()
                .remove(SessionPrefKey.TOKEN)
                .remove(SessionPrefKey.USER_ID)
                .apply();
    }

    public String getToken() {
        return sharedPreferences.getString(SessionPrefKey.TOKEN, null);
    }

    public String getUserId() {
        return sharedPreferences.getString(SessionPrefKey.USER_ID, null);
    }

    public boolean isActive() {
        return getToken() != null;
    }
}
