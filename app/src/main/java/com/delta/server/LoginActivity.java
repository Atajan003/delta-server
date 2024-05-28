package com.delta.server;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.delta.server.api.LoginNetwork;

public class LoginActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private LinearLayout inputLayout;
    private TextView loginBtn;
    private EditText editPassword;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private final String PASSWORD_KEY = "PASSWORD";
    private final String TOKEN_KEY = "TOKEN";


    public LoginActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setSharedPreference();

        setViewId();

        setConfNetwork();

        checkLogin(pref.getString(PASSWORD_KEY, ""));

        setConfListener();

    }

    private void setSharedPreference() {
        pref = getSharedPreferences(getResources().getString(R.string.app_name), MODE_PRIVATE);
        editor = pref.edit();
    }


    private void setViewId() {
        progressBar = findViewById(R.id.progress_bar);
        inputLayout = findViewById(R.id.edit_text_layout);
        loginBtn = findViewById(R.id.login_btn);
        editPassword = findViewById(R.id.edit_password);
    }


    private LoginNetwork network;

    private void setConfNetwork() {
        String deviceId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        network = new LoginNetwork(deviceId) {
            @Override
            public void onSuccess(String token) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                editor.putString(TOKEN_KEY, token);
                editor.commit();
            }

            @Override
            public void onError(int error) {
                if (error == 400) {
                    Toast.makeText(LoginActivity.this, "Your code not correct", Toast.LENGTH_SHORT).show();
                } else if (error == 403) {
                    Toast.makeText(LoginActivity.this, "Code is already used", Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.INVISIBLE);
                inputLayout.setVisibility(View.VISIBLE);
            }
        };
    }

    private void checkLogin(String password) {
        network.request(password);
    }

    private void setConfListener() {
        loginBtn.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
            inputLayout.setVisibility(View.INVISIBLE);

            String password = editPassword.getText().toString().trim();
            editor.putString(PASSWORD_KEY, password);
            editor.commit();
            checkLogin(password);

            editPassword.setText("");
        });
    }


}