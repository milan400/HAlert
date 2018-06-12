package com.example.milan.hospital;

import com.google.gson.annotations.SerializedName;

public class Disease {

    @SerializedName("name")
    String Disname;

    @SerializedName("symptoms")
    String Symptoms;

    public String getDisname() {
        return Disname;
    }

    public String getSymptoms() {
        return Symptoms;
    }


}
