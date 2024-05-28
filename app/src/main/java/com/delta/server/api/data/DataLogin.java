package com.delta.server.api.data;

public class DataLogin {
    private final boolean status;
    private final String msg;
    private final String token;

    public DataLogin(boolean status, String msg,String token) {
        this.status = status;
        this.msg = msg;
        this.token = token;
    }

    public boolean isStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public String getToken() {
        return msg;
    }
}
