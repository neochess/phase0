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
public class GeneratorMoveTestLeader {

    CoreBoard coreBoard;
    private CellBoard getCellEndLine() {
        coreBoard = new CoreBoard();

        return coreBoard.getCellByIndex(2, 0);
    }


    private CellBoard getDisposition(CoreFigure leftUpCoreFigure, CoreFigure upCoreFigure,
                                     CoreFigure rightUpCoreFigure, CoreFigure centerCoreFigure) {

        coreBoard = new CoreBoard();
        CellBoard leftUp, up, rightUp, center;

        leftUp = coreBoard.getCellByIndex(4, 4);
        leftUp.setCoreFigure(leftUpCoreFigure);

        rightUp = coreBoard.getCellByIndex(6, 6);
        rightUp.setCoreFigure(rightUpCoreFigure);

        up = coreBoard.getCellByIndex(5, 4);
        up.setCoreFigure(upCoreFigure);

        center = coreBoard.getCellByIndex(5, 5);
        center.setCoreFigure(centerCoreFigure);
        return center;
    }
    private CellBoard getDispositionFree(int x , int y , CoreFigure centerCoreFigure) {

        coreBoard = new CoreBoard();
        CellBoard center = coreBoard.getCellByIndex(x, y);
        center.setCoreFigure(centerCoreFigure);
        return center;

    }

    @Test
    public void testGetMoveLeader() throws Exception {

        CellBoard currCell = getDispositionFree(5,5, new CoreFigure(TypeGamer.White));
        IGeneratorMove generationMove = new GeneratorMoveLeader();
        List<Move> moves = generationMove.getMove(currCell, TypeGamer.White);
        assertTrue(moves.size() == 4);
    }

    @Test
    public void testGetMoveLeaderAttackOpponent() throws Exception {

        CellBoard currCell = getDisposition(new CoreFigure(TypeGamer.Black), new CoreFigure(TypeGamer.Black),
                new CoreFigure(TypeGamer.Black), new CoreFigure(TypeGamer.White));
        IGeneratorMove generationMove = new GeneratorMoveLeader();
        List<Move> moves = generationMove.getMove(currCell, TypeGamer.White);
        assertTrue(moves.size() == 4);
    }
}
