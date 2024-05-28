package com.delta.server.api;

import com.delta.server.api.data.DataLogin;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiUrl {
    @FormUrlEncoded
    @POST("/api/login")
    Call<DataLogin> checkLogin(@Field("device_id") String deviceId,
                               @Field("code") String password);

}
