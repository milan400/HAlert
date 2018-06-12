package com.example.milan.hospital;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient
{
    public static final String BASE_URL = "http://10.0.2.2/loginapp/";

    //public static final String BASE_URL = "https://nepalsofts.000webhostapp.com/";


    public static Retrofit retrofit = null;


    public static Retrofit getApiClient()
    {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        if(retrofit == null)
        {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();


        }
        return retrofit;
    }


}




































































