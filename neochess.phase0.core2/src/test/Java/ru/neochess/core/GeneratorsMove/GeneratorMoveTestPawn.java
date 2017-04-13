package ru.neochess.core.GeneratorsMove;

import org.junit.Test;
import ru.neochess.core.Board;
import ru.neochess.core.CellBoard;
import ru.neochess.core.Figure;
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

    Board board;
    private CellBoard getCellEndLine() {
        board = new Board();

        return board.getCellByIndex(2, 0);
    }


    private CellBoard getDisposition(Figure leftUpFigure, Figure upFigure,
                                     Figure rightUpFigure, Figure centerFigure) {

        board = new Board();
        CellBoard leftUp, up, rightUp, center;

        leftUp = board.getCellByIndex(0, 0);
        leftUp.setFigure(leftUpFigure);

        up = board.getCellByIndex(1, 0);
        up.setFigure(upFigure);

        rightUp = board.getCellByIndex(2, 0);
        rightUp.setFigure(rightUpFigure);

        center = board.getCellByIndex(1, 1);
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
        CellBoard currCell = getDisposition(new Figure(TypeGamer.White), new Figure(TypeGamer.White),
                new Figure(TypeGamer.White), new Figure(TypeGamer.Black));

        IGeneratorMove generationMove = new GeneratorMovePawn();
        List<Move> moves = generationMove.getMove(currCell, TypeGamer.White);
        assertNotNull("Список ходов не должен быть пустым", moves);


    }

    @Test
    public void testGetMoveFirstline() throws Exception {

        //с первой (2й) линии можно ходить на 2 клетки
        CellBoard currCell = getDispositionFree(1, 8 , new Figure( TypeGamer.White ));
        IGeneratorMove generationMove = new GeneratorMovePawn();
        List<Move> moves = generationMove.getMove(currCell, TypeGamer.White);
        assertTrue("У пешки должно быть 2 хода от 2й линии + превращение", moves.size() == 3);

    }

    @Test
    public void testGetMovePawnAttackLineOpponent() throws Exception {

        CellBoard currCell = getDisposition(new Figure(TypeGamer.White), new Figure(TypeGamer.White),
                new Figure(TypeGamer.White), new Figure(TypeGamer.Black));
        IGeneratorMove generationMove = new GeneratorMovePawn();
        List<Move> moves = generationMove.getMove(currCell, TypeGamer.White);
        assertTrue("Пешка должна рубить соперников по диагонали перед собой - 2 возможности + превращение",moves.size() == 3);
    }


}

