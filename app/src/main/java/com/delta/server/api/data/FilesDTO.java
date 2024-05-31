package com.delta.server.api.data;

public class FilesDTO {
    private final String name;
    private final int sstpCount;
    private final int byteSize;
    private boolean isChecked;

    public FilesDTO(String name, int sstpCount, int byteSize, boolean isChecked) {
        this.name = name;
        this.sstpCount = sstpCount;
        this.byteSize = byteSize;
        this.isChecked = isChecked;
    }

    public String getName() {
        return name;
    }

    public int getSstpCount() {
        return sstpCount;
    }

    public int getByteSize() {
        return byteSize;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
