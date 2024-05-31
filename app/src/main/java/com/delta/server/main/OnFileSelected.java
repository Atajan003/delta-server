package com.delta.server.main;

import com.delta.server.api.data.FilesDTO;

public interface OnFileSelected {
    void onFileSelect(FilesDTO file);

    void doneDialog();

}
