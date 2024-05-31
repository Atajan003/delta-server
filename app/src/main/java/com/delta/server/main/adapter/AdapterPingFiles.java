package com.delta.server.main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.delta.server.R;
import com.delta.server.api.data.PingFilesDTO;

import java.util.ArrayList;

public class AdapterPingFiles extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<PingFilesDTO> files;


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ping_files, parent, false);
        return new PingDataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (files == null) return;
        PingDataViewHolder mHolder = (PingDataViewHolder) holder;
        mHolder.bind(files.get(position));
    }

    @Override
    public int getItemCount() {
        return getFilesSize();
    }

    private int getFilesSize() {
        if (files == null) return 0;
        return files.size();
    }

    public void setFiles(ArrayList<PingFilesDTO> files) {
        this.files = files;
        notifyItemRangeChanged(0, files.size());
    }

    public void clearData() {
        if (files == null) return;
        files = null;
    }
}
