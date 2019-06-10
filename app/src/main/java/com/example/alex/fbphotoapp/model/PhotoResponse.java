package com.example.alex.fbphotoapp.model;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class PhotoResponse implements IResponse {

    private ArrayList<Data> data;

    @Override
    public String toString() {
        return "PhotoResponse{" +
                "data=" + data +
                '}';
    }

    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }

    public static final class Data {
        @SerializedName("id")
        @Nullable
        private String id;
        @SerializedName("source")
        @Nullable
        private String source;
        @SerializedName("created_time")
        @Nullable
        private String created_time;
        @SerializedName("album")
        @Nullable
        private PhotoResponse.Album album;

        @Nullable
        public String getId() {
            return id;
        }

        public void setId(@Nullable String id) {
            this.id = id;
        }

        @Nullable
        public String getSource() {
            return source;
        }

        public void setSource(@Nullable String source) {
            this.source = source;
        }

        @Nullable
        public String getCreated_time() {
            return created_time;
        }

        public void setCreated_time(@Nullable String created_time) {
            this.created_time = created_time;
        }

        @Nullable
        public PhotoResponse.Album getAlbum() {
            return album;
        }

        public void setAlbum(@Nullable PhotoResponse.Album album) {
            this.album = album;
        }
    }

    public static final class Album {

        @SerializedName("id")
        @Nullable
        private String id;
        @SerializedName("name")
        @Nullable
        private String name;
        @SerializedName("created_time")
        @Nullable
        private String created_time;

        @Nullable
        public String getId() {
            return id;
        }

        public void setId(@Nullable String id) {
            this.id = id;
        }

        @Nullable
        public String getName() {
            return name;
        }

        public void setName(@Nullable String name) {
            this.name = name;
        }

        @Nullable
        public String getCreated_time() {
            return created_time;
        }

        public void setCreated_time(@Nullable String created_time) {
            this.created_time = created_time;
        }
    }


}
