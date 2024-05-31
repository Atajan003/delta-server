package com.delta.server.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.delta.server.LoginActivity;
import com.delta.server.R;
import com.delta.server.api.GetFileDataNetwork;
import com.delta.server.api.data.PingFilesDTO;
import com.delta.server.api.data.FilesDTO;
import com.delta.server.main.adapter.AdapterPingFiles;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnFileSelected {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setViewId();

        initSystemUIViewListeners(rootContainer);

        setSharedPreference();

        setConfNetwork();

        setConfListener();

    }


    private LinearLayout rootContainer;
    private TextView uploadBtn;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private DialogFiles dialogFiles;
    private AdapterPingFiles adapterPingFiles;

    private void setViewId() {
        rootContainer = findViewById(R.id.root_container);
        uploadBtn = findViewById(R.id.upload_btn);
        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progress_bar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapterPingFiles = new AdapterPingFiles();
        recyclerView.setAdapter(adapterPingFiles);

        dialogFiles = new DialogFiles(this, token, this);
    }

    private int statusBarHeight;
    private int navigationBarHeight;

    private void initSystemUIViewListeners(ViewGroup rootContainer) {

        rootContainer.setOnApplyWindowInsetsListener((v, windowInsets) -> {
            WindowInsets defaultInsets = v.onApplyWindowInsets(windowInsets);
            statusBarHeight = defaultInsets.getSystemWindowInsetTop();
            navigationBarHeight = defaultInsets.getSystemWindowInsetBottom();

            return defaultInsets.replaceSystemWindowInsets(0, 0, 0, 0);
        });

        new Handler().postDelayed(() -> rootContainer.setPadding(0, statusBarHeight, 0, navigationBarHeight), 100);
    }

    private String token;

    private void setSharedPreference() {
        SharedPreferences sharedPreferences = getSharedPreferences(getResources().getString(R.string.app_name), MODE_PRIVATE);
        String TOKEN_KEY = "TOKEN";
        token = sharedPreferences.getString(TOKEN_KEY, "");
    }

    private GetFileDataNetwork getFileDataNetwork;

    private void setConfNetwork() {
        getFileDataNetwork = new GetFileDataNetwork(token) {
            @Override
            public void onSuccess(ArrayList<PingFilesDTO> response) {
                progressBar.setVisibility(View.GONE);
                adapterPingFiles.setFiles(response);
            }

            @Override
            public void onError() {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
            }
        };

        getFileDataNetwork.request("2024-05-12.json");
    }

    private void setConfListener() {
        uploadBtn.setOnClickListener(view -> dialogFiles.showDialog());
    }

    @Override
    public void onFileSelect(FilesDTO file) {

    }

    @Override
    public void doneDialog() {

    }
}