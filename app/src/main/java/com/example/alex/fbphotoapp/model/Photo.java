package com.example.alex.fbphotoapp.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

import org.jetbrains.annotations.Nullable;

public final class Photo {

    @DatabaseField(
            generatedId = true
    )
    @SerializedName("idField")
    private int idField;
    @DatabaseField
    @SerializedName("id")
    @Nullable
    private String id;
    @DatabaseField
    @SerializedName("created_time")
    @Nullable
    private String created_time;
    @DatabaseField
    @SerializedName("name")
    @Nullable
    private String name;
    @DatabaseField
    @SerializedName("url")
    @Nullable
    private String url;
    @DatabaseField
    @SerializedName("albumId")
    @Nullable
    private String albumId;


    public String getUrl() {
        return this.url;
    }

    public void setUrl(@Nullable String url) {
        this.url = url;
    }

    @Nullable
    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(@Nullable String created_time) {
        this.created_time = created_time;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    public int getIdField() {
        return idField;
    }

    public void setIdField(int idField) {
        this.idField = idField;
    }

    @Nullable
    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(@Nullable String albumId) {
        this.albumId = albumId;
    }

    @Nullable
    public String getId() {
        return id;
    }

    public Photo toPhoto(PhotoResponse.Data response) {
        this.url = response.getSource();
        PhotoResponse.Album album = response.getAlbum();
        this.name = album == null ? null : album.getName();
        this.albumId = album == null ? null : album.getId();
        this.id = response.getId();
        this.created_time = response.getCreated_time();
        return this;
    }

    public void setId(@Nullable String id) {
        this.id = id;
    }
}
