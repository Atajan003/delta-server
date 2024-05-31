package com.delta.server.main;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.delta.server.R;
import com.delta.server.api.GetFilesNetwork;
import com.delta.server.api.data.FilesDTO;
import com.delta.server.main.adapter.AdapterFiles;

import java.util.ArrayList;

public class DialogFiles {


    private final Context context;
    private final String token;
    private final OnFileSelected callBack;

    public DialogFiles(Context context, String token,OnFileSelected callBack) {
        this.context = context;
        this.token = token;
        this.callBack = callBack;

        setViewData();

        setConfNetwork();


    }

    private Dialog dialog;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private AdapterFiles adapterFiles;

    private void setViewData() {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.layout_files);
        dialog.setCanceledOnTouchOutside(false);
        TextView doneBtn = dialog.findViewById(R.id.done_btn);
        recyclerView = dialog.findViewById(R.id.files_rv);
        progressBar = dialog.findViewById(R.id.progress_bar);

        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        adapterFiles = new AdapterFiles(callBack);
        recyclerView.setAdapter(adapterFiles);

        doneBtn.setOnClickListener(view -> {
            callBack.doneDialog();
            dialog.dismiss();
        });

        if (dialog.getWindow() == null) return;
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }


    private GetFilesNetwork getFilesNetwork;

    private void setConfNetwork() {
        getFilesNetwork = new GetFilesNetwork(token) {
            @Override
            public void onSuccess(ArrayList<FilesDTO> response) {
                recyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                adapterFiles.setFiles(response);
            }

            @Override
            public void onError() {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(context, "Check your connection", Toast.LENGTH_SHORT).show();
            }
        };
    }


    public void showDialog() {
        recyclerView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        adapterFiles.clearData();
        getFilesNetwork.request();
        dialog.show();
    }
}
