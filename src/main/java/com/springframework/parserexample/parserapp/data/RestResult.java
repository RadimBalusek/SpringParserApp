package com.springframework.parserexample.parserapp.data;

//import lombok.Data;

//@Data
public class RestResult {

    private String name;
    private String format;
    private String time;
    private String resolution;
    private String size;
    private String Link;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    @Override
    public String toString() {
        return "RestResult{" +
                "name='" + name + '\'' +
                ", format='" + format + '\'' +
                ", time='" + time + '\'' +
                ", resolution='" + resolution + '\'' +
                ", size='" + size + '\'' +
                ", Link='" + Link + '\'' +
                '}';
    }
}