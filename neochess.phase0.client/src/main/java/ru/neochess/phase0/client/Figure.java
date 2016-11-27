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

    public String getRace() { return this.race;}

    public void setState(String state) {
        this.state = state;
    }

    public  void setCode(String code){
        this.code = code;
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

    public String getState(){
        return state;
    }


    public String encodeFigure() {
        return this.race+this.getCode()+this.state;
    }

    public int getCol() {
        int Col = 9;
        for(BoardCell c : cells){
            Col = c.getCol() < Col ? c.getCol(): Col;
        }
        return Col;
    }

    public int getRow() {
        int Row = 9;
        for(BoardCell c : cells){
            Row = c.getCol() < Row ? c.getRow(): Row;
        }
        return Row;
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


        copy.forEach(boardCell -> boardCell.clear());

        copy = null;
    }

    public void removeFromCell(BoardCell c){
        Iterator<BoardCell> it;
        for(it = cells.iterator(); it.hasNext();) {
            BoardCell nextC = it.next();
            if (c == nextC) { it.remove(); }
        }
    }

    public Board placeOnBoard(Board board, Map<String,Integer> row_col) {
        PlacementInterface pf = this.lib.getPlacementFunc();
        return (Board) pf.operation(board, row_col, this);
    }

}
