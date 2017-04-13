package ru.neochess.core;

import org.junit.Before;
import org.junit.Test;
import ru.neochess.core.Move.IMove;
import ru.neochess.core.Move.Move;

import static org.junit.Assert.*;
import static ru.neochess.core.Move.CharacteristicsMove.CheckElephant;
import static ru.neochess.core.Move.CharacteristicsMove.CheckKing;


/**
 * Тестируем класс Move
 * Created by diviz on 10.02.2017.
 */
public class MoveTest {
    Board board = new Board();
    private CellBoard from = board.getCellByIndex(0, 0);
    private CellBoard to = board.getCellByIndex(1, 1);
    private Figure figure = new Figure(TypeGamer.Black);
    private IMove move;

    @Before
    public void setUp() throws Exception {
        from.setFigure(figure);
        move = new Move(from, to, figure);
    }

    @Test
    public void make() throws Exception {
        move.make();
        assertNull("После хода старое поле должно очиститься", from.getFigure());
        assertEquals("Фигура должна переместится в поле", figure, move.getTo().getFigure());
    }

    @Test
    public void makeFromAndToEquals() throws Exception {
        Figure oldFigure = new Figure(TypeGamer.White);
        from.setFigure(oldFigure);
        move = new Move(from, from, figure);
        move.make();
        assertNotNull("Ход из = в", move.getFrom());
        assertEquals("Фигура должна переместится в поле", figure, move.getTo().getFigure());
    }

    @Test
    public void cancelFromAndToEquals() throws Exception {
        Figure oldFigure = new Figure(TypeGamer.White);
        from.setFigure(oldFigure);
        move = new Move(from, from, figure);
        move.make();
        move.cancel();
        assertNotNull("Ход из = в", move.getFrom());
        assertEquals("Фигура должна переместится в поле", oldFigure, move.getFrom().getFigure());
    }

    @Test
    public void makeDouble() throws Exception {
        move.make();
        move.make();
        assertNull("После хода старое поле должно очиститься", from.getFigure());
        assertEquals("Фигура должна переместится в поле", figure, move.getTo().getFigure());
    }

    @Test
    public void cancel() throws Exception {
        Figure oldFigure = new Figure(TypeGamer.White);
        to.setFigure(oldFigure);
        move = new Move(from, to, figure);
        move.make();
        move.cancel();
        assertEquals("На новом поле должна остаться старая фигура", oldFigure, move.getTo().getFigure());
        assertEquals("Фигура должна остаться в поле", figure, move.getFrom().getFigure());
    }

    @Test
    public void cancelVoid() throws Exception {
        move.cancel();
        assertNull("После отмены хода, новое поле должно очистится", to.getFigure());
        assertEquals("Фигура должна остаться в поле", figure, move.getFrom().getFigure());
    }

    @Test
    public void isCharacteristicsMoveElephant() throws  Exception {
        Figure elephant = new Figure(TypeGamer.Black, TypeFigure.Elephant);
        to.setFigure(elephant);
        move = new Move(from, to, figure);
        assertTrue("Бьем слона", move.isCharacteristicsMove(CheckElephant));
        assertFalse("Не бьем короля", move.isCharacteristicsMove(CheckKing));
    }

    @Test
    public void isCharacteristicsMoveKing() throws  Exception {
        Figure king = new Figure(TypeGamer.Black, TypeFigure.King);
        to.setFigure(king);
        move = new Move(from, to, figure);
        assertTrue("Бьем короля", move.isCharacteristicsMove(CheckKing));
        assertFalse("Не бьем слона", move.isCharacteristicsMove(CheckElephant));

    }
    @Test
    public void isCharacteristicsMoveLeader() throws  Exception {
        Figure leader = new Figure(TypeGamer.Black, TypeFigure.Leader);
        to.setFigure(leader);
        move = new Move(from, to, figure);
        assertTrue("Бьем лидера", move.isCharacteristicsMove(CheckKing));
        assertFalse("Не бьем слона", move.isCharacteristicsMove(CheckElephant));
    }
    @Test
    public void isCharacteristicsMoveNull() throws  Exception {
        assertFalse("Бьем пустоту", move.isCharacteristicsMove(CheckKing));
        assertFalse("Бьем пустоту", move.isCharacteristicsMove(CheckElephant));
    }

    @Test
    public void makeShot() throws Exception {
        from.setFigure(figure);
        to.setFigure(figure);
        move = new Move(from, to, figure, null);
        assertEquals("До хода поле заполенно", figure, from.getFigure());
        move.make();
        assertEquals("После хода поле заполенно", figure, move.getFrom().getFigure());
        assertNull("Куда ходили осталось пусто",  move.getTo().getFigure());

    }
    @Test
    public void cancelShot() throws Exception {
        from.setFigure(figure);
        to.setFigure(figure);
        move = new Move(from, to, figure, null);
        assertEquals("До хода поле заполенно", figure, from.getFigure());
        move.make();
        move.cancel();
        assertEquals("После хода поле заполенно", figure, move.getFrom().getFigure());
        assertEquals("Куда ходили осталось пусто",  figure, move.getTo().getFigure());

    }



}