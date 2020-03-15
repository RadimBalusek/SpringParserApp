package com.springframework.parserexample.parserapp.data;

public class MovieFormat {

    private String type;
    private boolean flag;

    public MovieFormat(String type, boolean flag) {
        this.type = type;
        this.flag = flag;
    }

    public String getMovieFormat() {
        return type;
    }

    public void setMovieFormat(String type) {
        this.type = type;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Format{" +
                "formatId='" + type + '\'' +
                ", flag=" + flag +
                '}';
    }
}
