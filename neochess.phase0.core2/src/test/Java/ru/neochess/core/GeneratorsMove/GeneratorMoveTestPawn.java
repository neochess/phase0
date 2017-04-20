package ru.neochess.core.GeneratorsMove;

import org.junit.Test;
import ru.neochess.core.CoreBoard;
import ru.neochess.core.CellBoard;
import ru.neochess.core.CoreFigure;
import ru.neochess.core.Move.Move;
import ru.neochess.core.TypeGamer;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Тест хода пешки
 * Created by diviz on 05.01.2017.
 */
public class GeneratorMoveTestPawn {

//    @Before

    CoreBoard coreBoard;
    private CellBoard getCellEndLine() {
        coreBoard = new CoreBoard();

        return coreBoard.getCellByIndex(2, 0);
    }


    private CellBoard getDisposition(CoreFigure leftUpCoreFigure, CoreFigure upCoreFigure,
                                     CoreFigure rightUpCoreFigure, CoreFigure centerCoreFigure) {

        coreBoard = new CoreBoard();
        CellBoard leftUp, up, rightUp, center;

        leftUp = coreBoard.getCellByIndex(0, 0);
        leftUp.setCoreFigure(leftUpCoreFigure);

        up = coreBoard.getCellByIndex(1, 0);
        up.setCoreFigure(upCoreFigure);

        rightUp = coreBoard.getCellByIndex(2, 0);
        rightUp.setCoreFigure(rightUpCoreFigure);

        center = coreBoard.getCellByIndex(1, 1);
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
    public void testGetMovePawnCellEndLine() throws Exception {
        CellBoard currCell = getCellEndLine();
        IGeneratorMove generation = new GeneratorMovePawn();
        List<Move> moves = generation.getMove(currCell, TypeGamer.White);
        assertTrue("В стену ходить нельзя",moves.size()  == 1);
    }


    @Test
    public void testGetMovePawnAttackCellEndLine() throws Exception {
        CellBoard currCell = getCellEndLine();
        IGeneratorMove generation = new GeneratorMovePawnAttack();
        assertTrue("В стену бить нельзя", generation.getMove(currCell, TypeGamer.White).isEmpty());
    }

    @Test
    public void testGetMovePawnCellLineOpponent() throws Exception {
        CellBoard currCell = getDisposition(new CoreFigure(TypeGamer.White), new CoreFigure(TypeGamer.White),
                new CoreFigure(TypeGamer.White), new CoreFigure(TypeGamer.Black));

        IGeneratorMove generationMove = new GeneratorMovePawn();
        List<Move> moves = generationMove.getMove(currCell, TypeGamer.White);
        assertNotNull("Список ходов не должен быть пустым", moves);


    }

    @Test
    public void testGetMoveFirstline() throws Exception {

        //с первой (2й) линии можно ходить на 2 клетки
        CellBoard currCell = getDispositionFree(1, 8 , new CoreFigure( TypeGamer.White ));
        IGeneratorMove generationMove = new GeneratorMovePawn();
        List<Move> moves = generationMove.getMove(currCell, TypeGamer.White);
        assertTrue("У пешки должно быть 2 хода от 2й линии + превращение", moves.size() == 3);

    }

    @Test
    public void testGetMovePawnAttackLineOpponent() throws Exception {

        CellBoard currCell = getDisposition(new CoreFigure(TypeGamer.White), new CoreFigure(TypeGamer.White),
                new CoreFigure(TypeGamer.White), new CoreFigure(TypeGamer.Black));
        IGeneratorMove generationMove = new GeneratorMovePawn();
        List<Move> moves = generationMove.getMove(currCell, TypeGamer.White);
        assertTrue("Пешка должна рубить соперников по диагонали перед собой - 2 возможности + превращение",moves.size() == 3);
    }


}

