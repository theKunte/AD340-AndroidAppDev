package com.example.helloworld.model;

public interface OnGetDataListener<T> {
    //this is for callbacks
    void onSuccess(T dataResponse);
    void onFailure();
}
