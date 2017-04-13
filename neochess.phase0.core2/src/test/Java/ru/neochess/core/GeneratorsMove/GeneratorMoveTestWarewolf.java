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
public class GeneratorMoveTestWarewolf {

    Board board;
    private CellBoard getCellEndLine() {
        board = new Board();

        return board.getCellByIndex(2, 0);
    }


    private CellBoard getDisposition(Figure leftUpFigure, Figure upFigure,
                                     Figure rightUpFigure, Figure centerFigure) {

        board = new Board();
        CellBoard leftUp, up, rightUp, center;

        leftUp = board.getCellByIndex(4, 4);
        leftUp.setFigure(leftUpFigure);

        rightUp = board.getCellByIndex(6, 6);
        rightUp.setFigure(rightUpFigure);

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
    public void testGetMoveWarewolf() throws Exception {

        CellBoard currCell = getDispositionFree(5,5, new Figure(TypeGamer.White));
        IGeneratorMove generationMove = new GeneratorMoveWerewolf();
        List<Move> moves = generationMove.getMove(currCell, TypeGamer.White);
        assertTrue("Кличество свободных ходов оборотня в позиции f5 на пустой доске должно быть 21",moves.size() == 21);
    }

    @Test
    public void testGetMoveWerewolAttackOpponent() throws Exception {

        CellBoard currCell = getDisposition(new Figure(TypeGamer.Black), new Figure(TypeGamer.Black),
                new Figure(TypeGamer.Black), new Figure(TypeGamer.White));
        IGeneratorMove generationMove = new GeneratorMoveWerewolf();
        List<Move> moves = generationMove.getMove(currCell, TypeGamer.White);
        assertTrue("Количество свободных ходов оборотня в позиции f5 на доске с двумя соперниками по диагонали должно быть 16", moves.size() == 16);
    }
}
