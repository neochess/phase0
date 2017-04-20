package ru.neochess.core;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by diviz on 05.01.2017.
 */
public class CellCoreBoardTest {
    @Test
    public void setFigure() throws Exception {
        CoreBoard coreBoard = new CoreBoard();
        CoreFigure coreFigure = new CoreFigure(TypeGamer.White);
        CellBoard cellA = coreBoard.getCellByIndex(0, 0);

        cellA.setCoreFigure(coreFigure);

        assertEquals(coreFigure, cellA.getCoreFigure());


    }

}