package com.delta.server.api;

import com.delta.server.api.data.DataLogin;
import com.delta.server.api.data.PingFilesDTO;
import com.delta.server.api.data.FilesDTO;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiUrl {
    @FormUrlEncoded
    @POST("/api/login")
    Call<DataLogin> checkLogin(@Field("device_id") String deviceId,
                               @Field("code") String password);

    @Headers({"Accept: application/json"})
    @GET("/api/data/files.json")
    Call<ArrayList<FilesDTO>> getFiles(@Header("Authorization") String token);

    @Headers({"Accept: application/json"})
    @GET
    Call<ArrayList<PingFilesDTO>> getFileData(@Header("Authorization") String token, @Url String url);

}
