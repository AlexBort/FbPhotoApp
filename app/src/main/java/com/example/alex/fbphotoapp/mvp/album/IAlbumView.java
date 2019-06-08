package com.example.alex.fbphotoapp.mvp.album;

import com.example.alex.fbphotoapp.model.Album;
import com.example.alex.fbphotoapp.mvp.base.IBaseView;

import java.util.ArrayList;
import java.util.List;

public interface IAlbumView extends IBaseView {

    void showAlbums(List<Album> list);

    void updateAlbum(Album album);

}
