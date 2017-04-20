package ru.neochess.phase0.client;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.List;
import java.util.function.BiFunction;
import java.io.File.*;
import java.lang.Class;
import java.lang.reflect.Method;
import java.util.*;


import ru.neochess.core.*;
import ru.neochess.core.GeneratorsMove.*;
import ru.neochess.core.GeneratorsMove.GeneratorMovePorcupine;
import ru.neochess.core.Move.Move;
import ru.neochess.core.CoreBoard;


/**
 * Created by for on 01.11.16.
 */
public class Figure {

   //статичные
    private ImageIcon img;
    private String code;
    private String race;
    private String state;
    private String moveGenerator;
    private Object moveGeneratorObj;
    private Class moveGeneratorClass;

    private ru.neochess.phase0.client.Board board;
    private ru.neochess.core.CoreBoard coreBoard;

    private ArrayList<BoardCell> cells = new ArrayList<>(); // массив клеток, которые занимает фигура
    private LibItem lib;

    //отвечающие за перемещение
    private int MousePos = -1;

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
        this.moveGenerator = lib.getMoveGenerator();

      /*  CustomClassLoader ccl = new CustomClassLoader();
        Object o;
        Class c;
        c = ccl.loadClass("someNewClass");*/

        try {
            moveGeneratorClass = Class.forName("ru.neochess.core.GeneratorsMove." + moveGenerator);

            // Class<?> movetstClass = Class.forName("Board");
              moveGeneratorObj = moveGeneratorClass.newInstance();
            // Object moveGeneratorObj = movetstClass.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

   public java.util.List<Move> getMoveGenerator(int x, int y)
   {
       java.util.List<Move> result = null;
       CellBoard cell;
       coreBoard = new CoreBoard();
       try {

       Method m = moveGeneratorClass.getMethod("getMove", CellBoard.class , TypeGamer.class );
          cell = coreBoard.getCellByIndex(x, y );
           try {
              result = (List<Move>) m.invoke(moveGeneratorObj, cell, TypeGamer.Black);
           } catch (IllegalAccessException e) {
               e.printStackTrace();
           } catch (InvocationTargetException e) {
               e.printStackTrace();
           }

       } catch (NoSuchMethodException e) {
       e.printStackTrace();
   }

       return result;}

    public void setMousePos (BoardCell c) //(int row, int col)
    {
         // BoardCell c = cells.stream().filter(c1 -> c1.getRow() == row && c1.getCol() == col).findAny().orElse(null);
        MousePos = cells.indexOf(c);
    }

    public int getMousePos ()
    {return MousePos;}

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

    public int getImageXshift()
    {
        int xshift = 0;
        switch (MousePos) {
            case -1: xshift = 0; break;

            case 0: xshift = 0; break;
            case 1: xshift = 1; break;
            case 2: xshift = 0; break;
            case 3: xshift = 1; break;

        }

    return xshift;}

    public int getImageYshift()
    { int a;
        if (this.getCode() == "H")
        a = 1;

        int yshift = 0;
        switch (MousePos) {
            case -1: yshift = 0; break;

            case 0: yshift = 0; break;
            case 1: yshift = 0; break;
            case 2: yshift = 1; break;
            case 3: yshift = 1; break;

        }

        return yshift;}
  /*  //для слоника
    public int getCol() {

        int a;
        if (MousePos > -1){
           // if (this.getCode() == "H")
               // a = 1;
        return cells.get(MousePos).getCol();}
        else
        return cells.get(0).getCol();
    }


    public int getRow() {
       if (MousePos > -1)
            return cells.get(MousePos).getRow();
        else
        return cells.get(0).getRow();
    }*/

    public int getxCoord() {
        int minX = 10*50 + 50; // правый столбец
        for(BoardCell c : cells){
            minX = c.getCol()*50 < minX ? c.getCol()*50 : minX;
        }
        return minX;
    }

    public int getyCoord() {
        int maxY = 10*50 + 50; // нижняя строка
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
    public String printNotation() {
        String mess = ""+this.getCode();

        if(cells==null || cells.isEmpty()) {
            mess += "клеток нет";
            System.out.println(mess);
            return mess;
        }

        for(BoardCell c : cells) {
            mess += String.valueOf((char)(c.getCol() + 'A')) + (10 - c.getRow());
        }

        return mess;
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
