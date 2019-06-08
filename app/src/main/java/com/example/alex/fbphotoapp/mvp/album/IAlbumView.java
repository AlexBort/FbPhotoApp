package com.example.alex.fbphotoapp.mvp.album;

import com.example.alex.fbphotoapp.model.Album;
import com.example.alex.fbphotoapp.mvp.base.IBaseView;

import java.util.ArrayList;

public interface IAlbumView extends IBaseView {

    void showAlbums(ArrayList<Album> list);

    void updateAlbum(Album album);

}
