package com.example.milan.hospital;

import com.google.gson.annotations.SerializedName;

public class GraphModel {

    @SerializedName("Images")
    private String images;

    public String getImages() {
        return images;
    }
}
