package com.springframework.parserexample.parserapp.data;

public class Resolution {

    public enum ResolutionType {

        // movie quality it's not possible detect by resolution depends also on codec

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

        private String resolutionType;

        ResolutionType(final String resolutionType) {
            this.resolutionType = resolutionType;
        }

        public String getResolutionType() {
            return resolutionType;
        }
    }

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
