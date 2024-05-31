package com.delta.server.main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.delta.server.R;
import com.delta.server.api.data.FilesDTO;
import com.delta.server.main.OnFileSelected;

import java.util.ArrayList;

public class AdapterFiles extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<FilesDTO> files;

    private final OnFileSelected callBack;

    public AdapterFiles(OnFileSelected callBack) {
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_files, parent, false);
        return new FilesViewHolder(itemView,callBack);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (files == null) return;
        FilesViewHolder mHolder = (FilesViewHolder) holder;
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

    public void setFiles(ArrayList<FilesDTO> files) {
        this.files = files;
        notifyItemRangeChanged(0, files.size());
    }

    public void clearData() {
        if (files == null) return;
        files = null;
    }
}
