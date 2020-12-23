package com.darsh.multipleimageselect.models;

import android.net.Uri;

/**
 * Created by Darshan on 4/14/2015.
 */
public class Album {
    public String name;
    public String cover;
    public Uri uri;

    public Album(String name, String cover, Uri uri) {
        this.name = name;
        this.cover = cover;
        this.uri = uri;
    }
}
