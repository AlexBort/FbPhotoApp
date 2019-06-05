package com.example.alex.fbphotoapp.mvp;

public interface GalleryContract {


    interface GalleryPresenter {
        void requestGallery();
    }

    interface GalleryView {
        void showGallery();

        void updateGallery();
    }
}
