package com.springframework.parserexample.parserapp.data;
import lombok.Data;

@Data
public class MovieFormat {

    private String type;
    private boolean flag;

    public MovieFormat(String type, boolean flag) {
        this.type = type;
        this.flag = flag;
    }

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

}
