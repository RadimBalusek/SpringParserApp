package com.springframework.parserexample.parserapp.data;

public class Resolution {

    private String type;
    private boolean flag;

    public Resolution(String type, boolean flag) {
        this.type = type;
        this.flag = flag;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Resolution{" +
                "type='" + type + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }
}
