package ru.neochess.core;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by diviz on 22.12.2016.
 */
public class CellTest {
    @Test
    public void toStringNotNull() throws Exception {
        Cell cell = new Cell(0, 0);

        Assert.assertNotNull(cell.toString());
    }

    @Test
    public void toStringInitialPosition() throws  Exception {
        Cell cell = new Cell(0,0);
        assertEquals(cell.toString(), "a10");
    }

    @Test
    public void toStringEndPosition() throws  Exception {
        Cell cell = new Cell(Cell.length,Cell.length);

        assertEquals(cell.toString(), "j1");
    }

    @Test
    public void toStringXGreaterMaximumLength() throws  Exception {
        int x = Cell.length + 1;
        Cell cell = new Cell(x,Cell.length);

        assertEquals("", cell.toString());
    }

    @Test
    public void toStringYGreaterMaximumLength() throws  Exception {
        int y = Cell.length + 1;
        Cell cell = new Cell(Cell.length, y);

        assertEquals("", cell.toString());
    }


}