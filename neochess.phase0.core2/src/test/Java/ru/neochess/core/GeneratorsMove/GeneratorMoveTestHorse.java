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
public class GeneratorMoveTestHorse {
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

    @Test
    public void testGetMoveHorseCell() throws Exception {
        CellBoard currCell = getDispositionFree(5, 5, new Figure(TypeGamer.Black) );
        // IGeneratorMove generationMove = new GeneratorMoveAgr();
        IGeneratorMove generationMove = new GeneratorMoveHorse();
        List<Move> moves = generationMove.getMove(currCell, TypeGamer.Black);
        assertTrue("У коня на свободной доске должно быть 8 ходов",moves.size() == 8);

    }
}
