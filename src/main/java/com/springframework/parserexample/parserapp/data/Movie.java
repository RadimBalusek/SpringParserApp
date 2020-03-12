package com.springframework.parserexample.parserapp.data;
import lombok.Data;

import java.util.List;


public class Movie {

    public enum FileName {

        CAM("cam"),
        HDCAM("hdcam"),
        KINO("kino");

        private String fileName;

        FileName(final String fileName) {
            this.fileName = fileName;
        }
        public String getFileName() {
            return fileName;
        }
    }

    public enum Currency {

        // dle rozlišení nelze přesně určit kvalita filmu, záleží na použitém kodeku   (můžu jen určit optimalni rozližení)
        NONE("none"),
        R1920x1080("1920x1080"),
        R1920x800("1920x800"),
        R1280x688("1280x688"),
        R1280x534("1280x534"),
        R728x302("728x302"),
        R720x384("720x384"),
        R720x304("720x304"),
        R720x300("720x300"),
        R704x380("704x380"),
        R688x288("688x288"),
        R640x346("640x346"),
        R640x344("640x344"),
        R640x272("640x272");

        private String currency;

        Currency(final String currency) {
            this.currency = currency;
        }
        public String getFormat() {
            return currency;
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
