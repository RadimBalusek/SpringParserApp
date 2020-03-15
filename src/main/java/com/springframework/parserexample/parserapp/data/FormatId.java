package com.springframework.parserexample.parserapp.data;

public enum FormatId {

    avi("AVI"),
    mkv("MKV"),
    rar("RAR"),
    mp4("MP4");

    private String formatId;

    FormatId(final String formatId) {
        this.formatId = formatId;
    }
    public String getFormatId() {
        return formatId;
    }
}
