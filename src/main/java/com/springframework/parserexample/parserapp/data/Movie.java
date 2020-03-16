package com.springframework.parserexample.parserapp.data;
import lombok.Data;

import java.util.List;


public class Movie {

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


    //  @Id
    private String name; // rambo

    private List<String> resolutions; // 1920x800

    private String formatId ; // avi

    private String  LanguageId; // EN, CZ

    private String TranslatingId; // tit, dabing

    private boolean camrip; // true

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getResolutions() {
        return resolutions;
    }

    public void setResolutions(List<String> resolutions) {
        this.resolutions = resolutions;
    }

    public String getFormatId() {
        return formatId;
    }

    public void setFormatId(String formatId) {
        this.formatId = formatId;
    }

    public String getLanguageId() {
        return LanguageId;
    }

    public void setLanguageId(String languageId) {
        LanguageId = languageId;
    }

    public String getTranslatingId() {
        return TranslatingId;
    }

    public void setTranslatingId(String translatingId) {
        TranslatingId = translatingId;
    }

    public boolean isCamrip() {
        return camrip;
    }

    public void setCamrip(boolean camrip) {
        this.camrip = camrip;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", resolution='" + resolutions + '\'' +
                ", formatId='" + formatId + '\'' +
                ", LanguageId='" + LanguageId + '\'' +
                ", TranslatingId='" + TranslatingId + '\'' +
                ", camrip=" + camrip +
                '}';
    }
}
