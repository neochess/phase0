package ru.neochess.core;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * IteratorCell Tester.
 *
 * @author diviz
 * @version 1.0
 * @since <pre>??? 10, 2016</pre>
 */
public class IteratorCellBoardTest {
    Board board = new Board();
    private CellBoard cellBoardLeft = board.getCellByIndex(0, 1);
    private CellBoard cellBoardLeftUp = board.getCellByIndex(0, 0);
    private CellBoard cellBoardLeftDown = board.getCellByIndex(0, 2);
    private CellBoard cellBoardRight = board.getCellByIndex(2, 1);
    private CellBoard cellBoardRightUp = board.getCellByIndex(2, 0);
    private CellBoard cellBoardRightDown = board.getCellByIndex(2, 2);
    private CellBoard cellBoardUp = board.getCellByIndex(1, 0);
    private CellBoard cellBoardDown = board.getCellByIndex(1, 2);

    private CellBoard core = board.getCellByIndex(1, 1);

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }



    /**
     * Method: hasNext()
     */
    @Test
    public void testHasNext() throws Exception {

        Iterator<CellBoard> iteratorCell;


        iteratorCell = core.getIterator(AdjacentCell.Left);
        assertTrue("Должен быть левый сосед", iteratorCell.hasNext());
        iteratorCell.next();
        assertFalse("Должен быть один левый сосед", iteratorCell.hasNext());

        iteratorCell = core.getIterator(AdjacentCell.Up);
        assertTrue("Должен быть верхний сосед", iteratorCell.hasNext());
        iteratorCell.next();
        assertFalse("Должен быть один верхний сосед", iteratorCell.hasNext());


        iteratorCell = core.getIterator(AdjacentCell.Right);
        assertTrue("Должен быть правый сосед", iteratorCell.hasNext());
        iteratorCell.next();
        assertFalse("Должен быть один правый сосед", iteratorCell.hasNext());

        iteratorCell = core.getIterator(AdjacentCell.Down);
        assertTrue("Должен быть нижний сосед", iteratorCell.hasNext());
        iteratorCell.next();
        assertFalse("Должен быть один нижний сосед", iteratorCell.hasNext());

        iteratorCell = core.getIterator(AdjacentCell.LeftUp);
        assertTrue("Должен быть сосед слева сверху", iteratorCell.hasNext());
        iteratorCell.next();
        assertFalse("Должен быть один слева сверху сосед", iteratorCell.hasNext());

        iteratorCell = core.getIterator(AdjacentCell.RightUp);
        assertTrue("Должен быть верхний сосед", iteratorCell.hasNext());
        iteratorCell.next();
        assertFalse("Должен быть один верхний сосед", iteratorCell.hasNext());

        iteratorCell = core.getIterator(AdjacentCell.RightDown);
        assertTrue("Должен быть нижний правый сосед", iteratorCell.hasNext());
        iteratorCell.next();
        assertFalse("Должен быть один нижний правый сосед", iteratorCell.hasNext());

        iteratorCell = core.getIterator(AdjacentCell.LeftDown);
        assertTrue("Должен быть левый нижний сосед", iteratorCell.hasNext());
        iteratorCell.next();
        assertFalse("Должен быть один левый нижний сосед", iteratorCell.hasNext());
    }




    /**
     * Method: next()
     */
    @Test
    public void testNext() throws Exception {
        Iterator<CellBoard> iteratorCell;
        CellBoard cellBoard;


        iteratorCell = core.getIterator(AdjacentCell.Left);
        cellBoard = iteratorCell.next();
        assertTrue("Левый сосед", cellBoard.toString().equals("a9"));

        iteratorCell = core.getIterator(AdjacentCell.Up);
        cellBoard = iteratorCell.next();
        assertTrue("Верхний сосед", cellBoard.toString().equals("b10"));

        iteratorCell = core.getIterator(AdjacentCell.Right);
        cellBoard = iteratorCell.next();
        assertTrue("Правый сосед", cellBoard.toString().equals("c9"));

        iteratorCell = core.getIterator(AdjacentCell.Down);
        cellBoard = iteratorCell.next();
        assertTrue("Нижний сосед", cellBoard.toString().equals("b8"));

        iteratorCell = core.getIterator(AdjacentCell.LeftUp);
        cellBoard = iteratorCell.next();
        assertTrue("Верхний левый", cellBoard.toString().equals("a10"));

        iteratorCell = core.getIterator(AdjacentCell.RightUp);
        cellBoard = iteratorCell.next();
        assertTrue("Правый верхний", cellBoard.toString().equals("c10"));

        iteratorCell = core.getIterator(AdjacentCell.RightDown);
        cellBoard = iteratorCell.next();
        assertTrue("Правый нижний", cellBoard.toString().equals("c8"));

        iteratorCell = core.getIterator(AdjacentCell.LeftDown);
        cellBoard = iteratorCell.next();
        assertTrue("Левый нижний", cellBoard.toString().equals("a8"));

    }

    /**
     * Method: remove()
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testRemove() throws Exception {
        IteratorCell iterator = core.getIterator(AdjacentCell.Up);
        iterator.remove();
    }


} 
