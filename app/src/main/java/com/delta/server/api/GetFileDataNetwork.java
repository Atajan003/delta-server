package com.delta.server.api;


import androidx.annotation.NonNull;

import com.delta.server.api.data.PingFilesDTO;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class GetFileDataNetwork {
    public abstract void onSuccess(ArrayList<PingFilesDTO> response);

    public abstract void onError();

    private final String token;

    public GetFileDataNetwork(String token) {
        this.token = token;
    }

    public void request(String nameData) {
        ApiUrl request = (ApiUrl) ApiClient.createRequest(ApiUrl.class);

        String url = "/api/data/" + nameData;

        Call<ArrayList<PingFilesDTO>> userCall = request.getFileData(token, url);

        userCall.enqueue(new Callback<ArrayList<PingFilesDTO>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<PingFilesDTO>> call, @NonNull Response<ArrayList<PingFilesDTO>> response) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        onSuccess(response.body());
                    } else {
                        onError();
                    }
                } else {
                    onError();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<PingFilesDTO>> call, @NonNull Throwable t) {
                onError();
            }

        });
    }
}
