package ru.neochess.core.GeneratorsMove;

import org.junit.Test;
import ru.neochess.core.CoreBoard;
import ru.neochess.core.CellBoard;
import ru.neochess.core.CoreFigure;
import ru.neochess.core.Move.Move;
import ru.neochess.core.TypeGamer;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by TiJi on 11.04.17.
 */
public class GeneratorMoveTestHorse {
    CoreBoard coreBoard;
    private CellBoard getCellEndLine() {
        coreBoard = new CoreBoard();

        return coreBoard.getCellByIndex(2, 0);
    }

    private CellBoard getDispositionFree(int x , int y , CoreFigure centerCoreFigure) {

        coreBoard = new CoreBoard();
        CellBoard center = coreBoard.getCellByIndex(x, y);
        center.setCoreFigure(centerCoreFigure);
        return center;

    }

    @Test
    public void testGetMoveHorseCell() throws Exception {
        CellBoard currCell = getDispositionFree(5, 5, new CoreFigure(TypeGamer.Black) );
        // IGeneratorMove generationMove = new GeneratorMoveAgr();
        IGeneratorMove generationMove = new GeneratorMoveHorse();
        List<Move> moves = generationMove.getMove(currCell, TypeGamer.Black);
        assertTrue("У коня на свободной доске должно быть 8 ходов",moves.size() == 8);

    }
}
