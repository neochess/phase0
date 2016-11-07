package ru.neochess.phase0.client;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Created by for on 01.11.16.
 */
public class Figure {
    private ImageIcon img;
    private String code;
    private String race;
    private String state;

    private ArrayList<BoardCell> cells = new ArrayList<>(); // массив клеток, которые занимает фигура

    private LibItem lib;

    public Figure(LibItem lib) {
        this.lib = lib;
        this.code = lib.getCode();
//        if(this.code == "L") {
//            System.out.println("archer detected");
//        }
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

    public int getxCoord() {
        int minX = 10*50; // правый столбец
        for(BoardCell c : cells){
            minX = c.getCol()*50 < minX ? c.getCol()*50 : minX;
        }
        return minX;
    }

    public int getyCoord() {
        int maxY = 10*50; // нижняя строка
        for(BoardCell c : cells){
            maxY = c.getRow()*50 < maxY ? c.getRow()*50 : maxY;
        }
        return maxY;
    }

    public void intoCell(BoardCell c){
        this.cells.add(c);
    }

    public void printCells() {
        String mess = ""+this.getDesc()+", Клетки:";

        if(cells==null || cells.isEmpty()) {
            mess += "клеток нет";
            System.out.println(mess);
            return;
        }

        for(BoardCell c : cells) {
            mess += "  "+c.getRow()+":"+c.getCol();
        }

        System.out.println(mess);
    }

    public void beaten() {
        // говорит всем своим клеткам очиститься
        // каждая клетка в свою очередь скажет этой фигуре removeFromCell

        //копия массива клеток
        ArrayList<BoardCell> copy = (ArrayList)cells.clone();

        for(Iterator<BoardCell> it = copy.iterator(); it.hasNext();) {
            BoardCell nextC = it.next();
            nextC.clear();
        }

        copy = null;
    }

    public void removeFromCell(BoardCell c){
        for(Iterator<BoardCell> it = cells.iterator(); it.hasNext();) {
            BoardCell nextC = it.next();
            if (c == nextC) { it.remove(); }
        }
    }

    public Board placeOnBoard(Board board, Map<String,Integer> row_col) {
        PlacementInterface pf = this.lib.getPlacementFunc();
        return (Board) pf.operation(board, row_col, this);
    }

}
