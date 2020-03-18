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
}
