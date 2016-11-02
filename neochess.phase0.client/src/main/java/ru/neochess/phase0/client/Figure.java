package ru.neochess.phase0.client;

import javax.swing.*;
import java.awt.*;

/**
 * Created by for on 01.11.16.
 */
public class Figure {
    private ImageIcon img;
    private String code;
    private String race;
    private String state;

    private LibItem lib;

    public Figure(LibItem lib) {
        this.lib = lib;
        this.code = lib.getCode();
        try {
            this.img = new ImageIcon(getClass().getResource(lib.getImgPath()));
        } catch (Exception e) {
            this.img = null;
        }
    }

    public Figure(String race, String state) {
        setRace(race);
        setState(state);
    }

    public void setRace(String race) {
        this.race = race;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public Image getImage() {
        return this.img.getImage();
    }

    public String getDesc() {
        return this.lib.getDesc();
    }

    public String encodeFigure() {
        return this.race+this.getCode()+this.state;
    }

}
