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
            add(new LibItem("A", "Агр", "/figures/animals/agr.gif", (board, rc, figure)->{
                board.getCellByIndex(rc.get("row"), rc.get("col")).placeIn(figure);
                return board; } ));

            add(new LibItem("B", "Дикобраз", "/figures/animals/dkbrz.gif", (board, rc, figure)->{
                board.getCellByIndex(rc.get("row"), rc.get("col")).placeIn(figure);
                return board; } ));

            add(new LibItem("C", "Дракон", "/figures/animals/drakon.gif", (board, rc, figure)->{
                board.getCellByIndex(rc.get("row"), rc.get("col")).placeIn(figure);
                return board; } ));

            add(new LibItem("D", "Конь", "/figures/animals/kon.gif", (board, rc, figure)->{
                board.getCellByIndex(rc.get("row"), rc.get("col")).placeIn(figure);
                return board; } ));

            add(new LibItem("E", "Носорог", "/figures/animals/nosrg.gif", (board, rc, figure)->{
                board.getCellByIndex(rc.get("row"), rc.get("col")).placeIn(figure);
                return board; } ));

            add(new LibItem("F", "Оборотень", "/figures/animals/obrtn.gif", (board, rc, figure)->{
                board.getCellByIndex(rc.get("row"), rc.get("col")).placeIn(figure);
                return board; } ));

            add(new LibItem("G", "Вожак", "/figures/animals/vojak.gif", (board, rc, figure)->{
                board.getCellByIndex(rc.get("row"), rc.get("col")).placeIn(figure);
                return board; } ));

            add(new LibItem("H", "Слон", "/figures/animals/slon.gif", (board, rc, figure)->{
                board.getCellByIndex(rc.get("row"), rc.get("col")).placeIn(figure);
                board.getCellByIndex(rc.get("row")+1, rc.get("col")).placeIn(figure);
                board.getCellByIndex(rc.get("row"), rc.get("col")+1).placeIn(figure);
                board.getCellByIndex(rc.get("row")+1, rc.get("col")+1).placeIn(figure);
                return board; } ));

// -------------------------------------

            add(new LibItem("I", "Король", "/figures/humans/korol.gif", (board, rc, figure)->{
                board.getCellByIndex(rc.get("row"), rc.get("col")).placeIn(figure);
                return board; } ));

            add(new LibItem("J", "Королева", "/figures/humans/krlva.gif", (board, rc, figure)->{
                board.getCellByIndex(rc.get("row"), rc.get("col")).placeIn(figure);
                return board; } ));

            add(new LibItem("K", "Ладья", "/figures/humans/ladya.gif", (board, rc, figure)->{
                board.getCellByIndex(rc.get("row"), rc.get("col")).placeIn(figure);
                return board; } ));

            add(new LibItem("L", "Лучник", "/figures/humans/luchnk.gif", (board, rc, figure)->{
                board.getCellByIndex(rc.get("row"), rc.get("col")).placeIn(figure);
                return board; } ));

            add(new LibItem("M", "Монах", "/figures/humans/monah.gif", (board, rc, figure)->{
                board.getCellByIndex(rc.get("row"), rc.get("col")).placeIn(figure);
                return board; } ));

            add(new LibItem("N", "Офицер", "/figures/humans/ofcr.gif", (board, rc, figure)->{
                board.getCellByIndex(rc.get("row"), rc.get("col")).placeIn(figure);
                return board; } ));

            add(new LibItem("O", "Пешка", "/figures/humans/peshka.gif", (board, rc, figure)->{
                board.getCellByIndex(rc.get("row"), rc.get("col")).placeIn(figure);
                return board; } ));

            add(new LibItem("P", "Ловушка", "/figures/humans/lvshk.gif", (board, rc, figure)->{
                board.getCellByIndex(rc.get("row"), rc.get("col")).placeIn(figure);
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
