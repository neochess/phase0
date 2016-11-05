package ru.neochess.phase0.client;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by for on 01.11.16.
 */
public class FiguresLibrary {

    private static FiguresLibrary instance = new FiguresLibrary();

    private List<LibItem> items = new ArrayList<LibItem>() {
        {
            add(new LibItem("A", "Король", "/figures/wking.gif", (board, rc, figure)->{
                board.getCellByIndex(rc.get("row"), rc.get("col")).placeIn(figure);
                return board; } ));

            add(new LibItem("S", "Слон", "/figures/bSLON.png", (board, rc, figure)->{
                board.getCellByIndex(rc.get("row"), rc.get("col")).placeIn(figure);
                board.getCellByIndex(rc.get("row")+1, rc.get("col")).placeIn(figure);
                board.getCellByIndex(rc.get("row"), rc.get("col")+1).placeIn(figure);
                board.getCellByIndex(rc.get("row")+1, rc.get("col")+1).placeIn(figure);

                return board; } ));
        }
    };

    public static FiguresLibrary init() {
        //instance.addItem(new LibItem("A", "Слон"));

        return instance;
    }

//    private void addItem(LibItem item){
//        items.add(item);
//    }

    private LibItem getItemByCode(String code) {
        for(LibItem lib : items) {
            if (lib!=null && lib.getCode().equalsIgnoreCase(code)){
                return lib;
            }
        }
        return null;
    }

    public Figure getFigureByCode(String code) {
        return new Figure(this.getItemByCode(code));
    }

//    public Figure getEmptyFigure() {
//        return getFigureByCode("Z");
//    }

}
