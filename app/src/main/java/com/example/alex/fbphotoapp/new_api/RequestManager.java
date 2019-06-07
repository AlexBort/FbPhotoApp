package com.example.alex.fbphotoapp.new_api;


import com.example.alex.fbphotoapp.model.AlbumResponse;
import com.example.alex.fbphotoapp.model.IResponse;
import com.example.alex.fbphotoapp.model.PhotoResponse;
import com.facebook.AccessToken;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestManager {

    private final static List<Integer> successStatusCodes = new ArrayList<Integer>() {{
        add(200);
        add(302);
        add(400);
        add(401);
        add(404);
    }};
    private ApiManager manager = new ApiManager();


    private <T extends IResponse> Callback<T> createCallback(final Class<T> responseClazz, final ResponseListener<T> listener) {
        return new Callback<T>() {
            @Override
            public void onResponse(retrofit2.Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    listener.successResponse(response.body());
//                    if (LogApi.isLoggable()) {
//                        LogApi.logInfo("successful response: " + response.body().toString());
//                    }

                } else {
                    if (successStatusCodes.contains(response.code())) {
                        try {
                            Gson gson = new Gson();
                            T responseData = gson.fromJson(response.errorBody().string(), responseClazz);
                            listener.successResponse(responseData);
//                            if (LogApi.isLoggable()) {
//                                LogApi.logInfo("not successful response: " + responseData.toString());
//                            }

                        } catch (Exception e) {
                            listener.failureResponse();
//                            if (LogApi.isLoggable()) {
//                                LogApi.logError("error: " + e.getMessage());
//                            }
                        }
                    } else {
                        listener.failureResponse();
//                        if (LogApi.isLoggable()) {
//                            LogApi.logError("Unhandled error");
//                        }
                    }
                }
            }

            @Override
            public void onFailure(retrofit2.Call<T> call, Throwable t) {
                listener.failureResponse();
//                if (LogApi.isLoggable()) {
//                    LogApi.logError("failure: " + t.getMessage());
//                }
            }
        };
    }


    public final void getPhotos(AccessToken token, final ResponseListener listener) {
//        Call<PhotoResponse> call = manager.getRequestsApi().
//                getPhotos();
//        call.enqueue(createCallback(PhotoResponse.class, listener));
    }

    public final void getAlbums(AccessToken token, final ResponseListener listener) {
        Call<AlbumResponse> call = manager.getRequestsApi().
                getAlbums(token.getUserId(), token.getToken(), "picture,name,created_time");
        call.enqueue(createCallback(AlbumResponse.class, listener));
    }

}
