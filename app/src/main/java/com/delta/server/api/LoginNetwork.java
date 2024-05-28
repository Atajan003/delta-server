package com.delta.server.api;


import androidx.annotation.NonNull;

import com.delta.server.api.data.DataLogin;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class LoginNetwork {
    public abstract void onSuccess(String token);

    public abstract void onError(int error);

    private final String deviceId;

    public LoginNetwork(String deviceId) {
        this.deviceId = deviceId;
    }

    public void request(String password) {
        ApiUrl request = (ApiUrl) ApiClient.createRequest(ApiUrl.class);

        Call<DataLogin> userCall = request.checkLogin(deviceId, password);

        userCall.enqueue(new Callback<DataLogin>() {
            @Override
            public void onResponse(@NonNull Call<DataLogin> call, @NonNull Response<DataLogin> response) {
                if (response.code() == 200) {
                    if (response.body() != null && response.body().isStatus()) {
                        onSuccess(response.body().getToken());
                    } else {
                        onError(response.code());
                    }
                } else {
                    onError(response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<DataLogin> call, @NonNull Throwable t) {
                onError(0);
            }

        });
    }
}
