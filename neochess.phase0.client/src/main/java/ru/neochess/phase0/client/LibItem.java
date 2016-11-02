package ru.neochess.phase0.client;

/**
 * Created by for on 01.11.16.
 */
public class LibItem {
    private String code;
    private String desc;
    private String imgPath;

    public LibItem(String code, String desc, String imgPath) {
        this.code = code;
        this.desc = desc;
        this.imgPath = imgPath;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public String getImgPath() {
        return imgPath;
    }
}
