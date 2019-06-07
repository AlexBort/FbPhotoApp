package com.example.alex.fbphotoapp.new_api;

import com.example.alex.fbphotoapp.model.IResponse;

public interface ResponseListener<T extends IResponse> {
    void successResponse(T response);

    void failureResponse();
}
