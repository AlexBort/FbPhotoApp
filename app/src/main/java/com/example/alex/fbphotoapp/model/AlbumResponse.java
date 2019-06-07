package com.example.alex.fbphotoapp.model;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public final class AlbumResponse implements IResponse {


    @SerializedName("data")
    @Nullable
    private ArrayList data;


    @Override
    public String toString() {
        return "AlbumResponse{" +
                "data=" + data +
                '}';
    }

    @Nullable
    public final ArrayList getData() {
        return this.data;
    }

    public final void setData(@Nullable ArrayList var1) {
        this.data = var1;
    }

    public static final class Data {
        @SerializedName("picture")
        @Nullable
        private AlbumResponse.Picture picture;
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
        public final AlbumResponse.Picture getPicture() {
            return this.picture;
        }

        public final void setPicture(@Nullable AlbumResponse.Picture var1) {
            this.picture = var1;
        }

        @Nullable
        public final String getId() {
            return this.id;
        }

        public final void setId(@Nullable String var1) {
            this.id = var1;
        }

        @Nullable
        public final String getName() {
            return this.name;
        }

        public final void setName(@Nullable String var1) {
            this.name = var1;
        }

        @Nullable
        public final String getCreated_time() {
            return this.created_time;
        }

        public final void setCreated_time(@Nullable String var1) {
            this.created_time = var1;
        }

        @NotNull
        public String toString() {
            return "Data(picture=" + this.picture + ", id=" + this.id + ", name=" + this.name + ", created_time=" + this.created_time + ')';
        }
    }


    public static final class Picture {
        @SerializedName("data")
        @Nullable
        private AlbumResponse.Datum data;

        @Nullable
        public final AlbumResponse.Datum getData() {
            return this.data;
        }

        public final void setData(@Nullable AlbumResponse.Datum var1) {
            this.data = var1;
        }

        @NotNull
        public String toString() {
            return "Picture(data=" + this.data + ')';
        }
    }


    public static final class Datum {
        @SerializedName("url")
        @Nullable
        private String url;
        @SerializedName("is_silhouette")
        @Nullable
        private String is_silhouette;

        @Nullable
        public final String getUrl() {
            return this.url;
        }

        public final void setUrl(@Nullable String var1) {
            this.url = var1;
        }

        @Nullable
        public final String is_silhouette() {
            return this.is_silhouette;
        }

        public final void set_silhouette(@Nullable String var1) {
            this.is_silhouette = var1;
        }

        @NotNull
        public String toString() {
            return "Datum(url=" + this.url + ", is_silhouette=" + this.is_silhouette + ')';
        }
    }





}
