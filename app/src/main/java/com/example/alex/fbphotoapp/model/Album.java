package com.example.alex.fbphotoapp.model;

import android.graphics.Picture;
import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

import org.jetbrains.annotations.NotNull;

public final class Album {


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
    @SerializedName("name")
    @Nullable
    private String name;
    @DatabaseField
    @SerializedName("created_time")
    @Nullable
    private String created_time;
    @DatabaseField
    @SerializedName("url")
    @Nullable
    private String url;

    public final int getIdField() {
        return this.idField;
    }

    public final void setIdField(int var1) {
        this.idField = var1;
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

    @Nullable
    public final String getUrl() {
        return this.url;
    }

    public final void setUrl(@Nullable String var1) {
        this.url = var1;
    }

//    @NotNull
//    public final Album convertToAlbum(@NotNull Data picture) {
//
//    }

    public boolean equals(@Nullable Object other) {
        return true;
    }

    public int hashCode() {
        return 0;
    }


}
