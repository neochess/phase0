package ru.neochess.core;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by diviz on 05.01.2017.
 */
public class CellBoardTest {
    @Test
    public void setFigure() throws Exception {
        Board board = new Board();
        Figure figure = new Figure(TypeGamer.White);
        CellBoard cellA = board.getCellByIndex(0, 0);

        cellA.setFigure(figure);

        assertEquals(figure, cellA.getFigure());


    }

}