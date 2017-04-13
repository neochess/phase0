package ru.neochess.core.GeneratorsMove;

import org.junit.Test;
import ru.neochess.core.Board;
import ru.neochess.core.CellBoard;
import ru.neochess.core.Figure;
import ru.neochess.core.Move.Move;
import ru.neochess.core.TypeGamer;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by TiJi on 11.04.17.
 */
public class GeneratorMoveTestPawnAttack {

    Board board;
    private CellBoard getCellEndLine() {
        board = new Board();

        return board.getCellByIndex(2, 0);
    }


    private CellBoard getDisposition(Figure leftUpFigure, Figure upFigure,
                                     Figure rightUpFigure, Figure centerFigure) {

        board = new Board();
        CellBoard leftUp, up, rightUp, center;

        leftUp = board.getCellByIndex(3, 3);
        leftUp.setFigure(leftUpFigure);

        up = board.getCellByIndex(5, 4);
        up.setFigure(rightUpFigure);

        center = board.getCellByIndex(5, 5);
        center.setFigure(centerFigure);
        return center;
    }
    private CellBoard getDispositionFree(int x , int y , Figure centerFigure) {

        board = new Board();
        CellBoard center = board.getCellByIndex(x, y);
        center.setFigure(centerFigure);
        return center;

    }

    @Test
    public void testGetMovePawnAttack() throws Exception {

        CellBoard currCell = getDispositionFree(5,5, new Figure(TypeGamer.White));
        IGeneratorMove generationMove = new GeneratorMovePawnAttack();
        List<Move> moves = generationMove.getMove(currCell, TypeGamer.White);
        assertTrue("количество ходов у боевой пеши на пустой доске 1 ",moves.size() == 1);
    }

    @Test
    public void testGetMoveRinocerosAttackOpponent() throws Exception {

        CellBoard currCell = getDisposition(new Figure(TypeGamer.Black), new Figure(TypeGamer.Black),
                new Figure(TypeGamer.Black), new Figure(TypeGamer.White));
        IGeneratorMove generationMove = new GeneratorMovePawnAttack();
        List<Move> moves = generationMove.getMove(currCell, TypeGamer.White);
        assertTrue(moves.size() == 1);
    }
}
