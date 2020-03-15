package com.springframework.parserexample.parserapp.data;

public enum CamRip {

    CAM("cam"),
    HDCAM("hdcam"),
    KINO("kino");

    private String fileName;

    CamRip(final String fileName) {
        this.fileName = fileName;
    }
    public String getFileName() {
        return fileName;
    }
}
