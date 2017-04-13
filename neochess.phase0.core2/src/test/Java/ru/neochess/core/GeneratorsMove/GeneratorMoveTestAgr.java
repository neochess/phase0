package ru.neochess.core.GeneratorsMove;

import org.junit.Test;
import ru.neochess.core.*;
import ru.neochess.core.Move.Move;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by TiJi on 02.04.17.
 */
public class GeneratorMoveTestAgr {

    Board board;
    private CellBoard getCellEndLine() {
        board = new Board();

        return board.getCellByIndex(2, 0);
    }

    private CellBoard getDispositionFree(int x , int y , Figure centerFigure) {

        board = new Board();
        CellBoard center = board.getCellByIndex(x, y);
        center.setFigure(centerFigure);
        return center;

    }
    private CellBoard getDispositionLeader(Figure centerFigure)
    {
        board = new Board();
        CellBoard up, center;

        up = board.getCellByIndex(1, 0);
        up.setFigure(new Figure(TypeGamer.Black, TypeFigure.Leader));


        center = board.getCellByIndex(1, 1);
        center.setFigure(centerFigure);
        return center;
    }

    private CellBoard getDisposition(Figure leftDownFigure, Figure downFigure,
                                     Figure rightDownFigure, Figure centerFigure) {

        board = new Board();
        CellBoard leftUp, up, rightUp, center;

        leftUp = board.getCellByIndex(0, 2);
        leftUp.setFigure(leftDownFigure);

        up = board.getCellByIndex(1, 2);
        up.setFigure(downFigure);

        rightUp = board.getCellByIndex(2, 2);
        rightUp.setFigure(rightDownFigure);

        center = board.getCellByIndex(1, 1);
        center.setFigure(centerFigure);
        return center;
    }

    /*private CellBoard getDispositionFree(Figure centerFigure) {
        CellBoard leftUp, up, rightUp, center, down, leftDown, rightDown, left, right;
        leftUp = new CellBoard(0, 0, null);
        left = new CellBoard(0, 1, null);
        leftDown = new CellBoard(0, 2, null);
        up = new CellBoard(1, 0, null);
        down = new CellBoard(1, 2, null);
        right = new CellBoard(2, 1, null);
        rightUp = new CellBoard(2, 0, null);
        rightDown = new CellBoard(2, 2, null);

      /*  center = new CellBoard(1, 1, centerFigure, null, null, up,
                null, null, null,
                leftUp, rightUp );

        center = new CellBoard(1, 1, centerFigure,  left, right, up, down, leftDown, rightDown, leftUp, rightUp);
        return center;
    }*/

    @Test
    public void testGetMovePawnCellEndLine() throws Exception {
        CellBoard currCell = getCellEndLine();
        IGeneratorMove generation = new GeneratorMovePawn();

        assertTrue("В стену ходить нельзя", generation.getMove(currCell, TypeGamer.Black).isEmpty());
    }
  //  @Test
  /*  public void testGetMovePawnCellLineOpponent() throws Exception {
        CellBoard currCell = getDisposition(new Figure(TypeGamer.White), new Figure(TypeGamer.White),
                new Figure(TypeGamer.White), new Figure(TypeGamer.Black));

        IGeneratorMove generationMove = new GeneratorMovePawn();

        List<Move> moves = generationMove.getMove(currCell, TypeGamer.White);

        assertNotNull("Список ходов не должен быть пустым", moves);

    }*/

    @Test
    public void testGetMoveAgrCell() throws Exception {
        CellBoard currCell = getDispositionFree(5, 5, new Figure(TypeGamer.Black) );
        IGeneratorMove generationMove = new GeneratorMoveAgr();
        List<Move> moves = generationMove.getMove(currCell, TypeGamer.Black);
        assertNotNull(moves.size());

    }

    @Test
    public void testGetMoveFirstline() throws Exception {

        //сo первой (2й) (8й) линии можно ходить на 2 клетки
        CellBoard currCell = getDispositionFree(1, 1 , new Figure( TypeGamer.Black ));
        IGeneratorMove generationMove = new GeneratorMoveAgr();
        List<Move> moves = generationMove.getMove(currCell, TypeGamer.Black);
        assertTrue("С начальной позиции агр должен ходить на 2 клетки",moves.size() == 2);

    }

    @Test
    public void testGetMoveFirstlineLeader() throws Exception {

        //от вожака можно ходить на три клетки
        CellBoard currCell = getDispositionLeader(new Figure( TypeGamer.Black ));
        IGeneratorMove generationMove = new GeneratorMoveAgr();
        List<Move> moves = generationMove.getMove(currCell, TypeGamer.Black);
        assertTrue("От вожака агр должен ходить на 3 клетки",moves.size() == 3);

    }

    @Test
    public void testGetMovePawnAttackLineOpponent() throws Exception {

        CellBoard currCell = getDisposition(new Figure(TypeGamer.White), new Figure(TypeGamer.White),
                new Figure(TypeGamer.White), new Figure(TypeGamer.Black));
        IGeneratorMove generationMove = new GeneratorMoveAgr();
        List<Move> moves = generationMove.getMove(currCell, TypeGamer.Black);
        assertTrue("Агр должен рубить 3 клетки перед собой",moves.size() == 3);
    }
}
