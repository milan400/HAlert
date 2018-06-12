package com.example.milan.hospital;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface
{
    @GET("register.php")
    Call<User> performRegistration(@Query("name") String Name, @Query("user_name") String UserName, @Query("user_password") String UserPassword);

    @GET("login.php")
    Call<User> performUserLogin(@Query("user_name") String UserName,@Query("user_password") String UserPassword);

    @GET("getsymptoms.php")
    Call<List<Disease>>  getSymptoms(@Query("disesasename") String diseasename);

    @POST("getdiseasename.php")
    Call<List<Name>> getDiseasename();

    @POST("getzonename.php")
    Call<List<Name>> getZonename();

    @GET("getdistrict.php")
    Call<List<Name>> getDistrictname(@Query("zonename") String zonename);


    @GET("registerfrom.php")
    Call<FormData> performformregister(@Query("zone") String zone,@Query("district") String district,@Query("disease") String disease);


    @GET("getgraphname.php")
    Call<List<Name>> getgraphname();

    @GET("getgraph.php")
    Call<List<GraphModel>> getgraph(@Query("imagename") String imagename);

}














































































