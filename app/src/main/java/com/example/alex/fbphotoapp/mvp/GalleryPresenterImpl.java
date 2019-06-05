package com.example.alex.fbphotoapp.mvp;

public class GalleryPresenterImpl implements GalleryContract.GalleryPresenter {


    private GalleryContract.GalleryView view;

    // TODO: 05.06.2019 если не выйдет, сделать статикой

    public GalleryPresenterImpl(GalleryContract.GalleryView view) {
        this.view = view;
    }


    @Override
    public void requestGallery() {

    }
}
