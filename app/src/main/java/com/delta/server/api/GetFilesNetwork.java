package com.delta.server.api;


import androidx.annotation.NonNull;

import com.delta.server.api.data.FilesDTO;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class GetFilesNetwork {
    public abstract void onSuccess(ArrayList<FilesDTO> response);

    public abstract void onError();

    private final String token;

    public GetFilesNetwork(String token) {
        this.token = token;
    }

    public void request() {
        ApiUrl request = (ApiUrl) ApiClient.createRequest(ApiUrl.class);

        Call<ArrayList<FilesDTO>> userCall = request.getFiles(token);

        userCall.enqueue(new Callback<ArrayList<FilesDTO>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<FilesDTO>> call, @NonNull Response<ArrayList<FilesDTO>> response) {
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
            public void onFailure(@NonNull Call<ArrayList<FilesDTO>> call, @NonNull Throwable t) {
                onError();
            }

        });
    }
}
