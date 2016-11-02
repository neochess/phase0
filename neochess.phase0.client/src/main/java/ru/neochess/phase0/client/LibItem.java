package ru.neochess.phase0.client;

import java.util.Map;
import java.util.function.BiFunction;

/**
 * Created by for on 01.11.16.
 */
public class LibItem {
    private String code;
    private String desc;
    private String imgPath;
    private PlacementInterface placementFunc;

    public LibItem(String code, String desc, String imgPath, PlacementInterface p) {
        this.code = code;
        this.desc = desc;
        this.imgPath = imgPath;
        this.placementFunc = p;
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

    public PlacementInterface getPlacementFunc() {
        return placementFunc;
    }
}
