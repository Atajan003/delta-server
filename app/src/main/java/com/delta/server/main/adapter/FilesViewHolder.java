package com.delta.server.main.adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.delta.server.R;
import com.delta.server.api.data.FilesDTO;
import com.delta.server.main.OnFileSelected;

public class FilesViewHolder extends RecyclerView.ViewHolder {

    private final TextView nameFile;
    private final TextView sizeFile;
    private final CheckBox checkBox;
    private FilesDTO selectedFile;

    public FilesViewHolder(@NonNull View itemView, OnFileSelected callBack) {
        super(itemView);
        nameFile = itemView.findViewById(R.id.name_file);
        sizeFile = itemView.findViewById(R.id.size_file);
        checkBox = itemView.findViewById(R.id.checkbox);

        itemView.setOnClickListener(view -> {
            callBack.onFileSelect(selectedFile);
        });
    }

    public void bind(FilesDTO filesDTO) {
        selectedFile = filesDTO;
        nameFile.setText(filesDTO.getName());
        sizeFile.setText(String.valueOf(filesDTO.getSstpCount()));

    }
}
