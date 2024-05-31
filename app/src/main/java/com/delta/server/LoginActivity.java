package com.delta.server;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.delta.server.api.LoginNetwork;
import com.delta.server.main.MainActivity;

public class LoginActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private LinearLayout inputLayout;
    private TextView loginBtn;
    private EditText editPassword;
    private SharedPreferences sharedPreferences;
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

        checkLogin(sharedPreferences.getString(PASSWORD_KEY, ""));

        setConfListener();

    }

    private void setSharedPreference() {
        sharedPreferences = getSharedPreferences(getResources().getString(R.string.app_name), MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }


    private void setViewId() {
        progressBar = findViewById(R.id.progress_bar);
        inputLayout = findViewById(R.id.edit_text_layout);
        loginBtn = findViewById(R.id.login_btn);
        editPassword = findViewById(R.id.edit_password);

        inputLayout.setAlpha(0.5f);
        disableEnableControls(false, inputLayout);

        editPassword.setText(sharedPreferences.getString(PASSWORD_KEY, ""));

    }


    private LoginNetwork network;

    private void setConfNetwork() {
        String deviceId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        network = new LoginNetwork(deviceId) {
            @Override
            public void onSuccess(String token) {
                editor.putString(TOKEN_KEY, token);
                editor.commit();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onError(int error) {
                if (error == 400) {
                    Toast.makeText(LoginActivity.this, "Your code not correct", Toast.LENGTH_SHORT).show();
                } else if (error == 403) {
                    Toast.makeText(LoginActivity.this, "Code is already used", Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.INVISIBLE);
                inputLayout.setAlpha(1f);
                disableEnableControls(true, inputLayout);
            }
        };
    }

    private void checkLogin(String password) {
        network.request(password);
    }

    private void setConfListener() {
        loginBtn.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
            inputLayout.setAlpha(0.5f);
            disableEnableControls(false, inputLayout);

            String password = editPassword.getText().toString().trim();
            editor.putString(PASSWORD_KEY, password);
            editor.commit();
            checkLogin(password);

            editPassword.setText("");
        });
    }

    private void disableEnableControls(boolean enable, ViewGroup vg) {
        for (int i = 0; i < vg.getChildCount(); i++) {
            View child = vg.getChildAt(i);
            child.setEnabled(enable);
            if (child instanceof ViewGroup) {
                disableEnableControls(enable, (ViewGroup) child);
            }
        }
    }


}