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
    CoreBoard coreBoard = new CoreBoard();
    private CellBoard from = coreBoard.getCellByIndex(0, 0);
    private CellBoard to = coreBoard.getCellByIndex(1, 1);
    private CoreFigure coreFigure = new CoreFigure(TypeGamer.Black);
    private IMove move;

    @Before
    public void setUp() throws Exception {
        from.setCoreFigure(coreFigure);
        move = new Move(from, to, coreFigure);
    }

    @Test
    public void make() throws Exception {
        move.make();
        assertNull("После хода старое поле должно очиститься", from.getCoreFigure());
        assertEquals("Фигура должна переместится в поле", coreFigure, move.getTo().getCoreFigure());
    }

    @Test
    public void makeFromAndToEquals() throws Exception {
        CoreFigure oldCoreFigure = new CoreFigure(TypeGamer.White);
        from.setCoreFigure(oldCoreFigure);
        move = new Move(from, from, coreFigure);
        move.make();
        assertNotNull("Ход из = в", move.getFrom());
        assertEquals("Фигура должна переместится в поле", coreFigure, move.getTo().getCoreFigure());
    }

    @Test
    public void cancelFromAndToEquals() throws Exception {
        CoreFigure oldCoreFigure = new CoreFigure(TypeGamer.White);
        from.setCoreFigure(oldCoreFigure);
        move = new Move(from, from, coreFigure);
        move.make();
        move.cancel();
        assertNotNull("Ход из = в", move.getFrom());
        assertEquals("Фигура должна переместится в поле", oldCoreFigure, move.getFrom().getCoreFigure());
    }

    @Test
    public void makeDouble() throws Exception {
        move.make();
        move.make();
        assertNull("После хода старое поле должно очиститься", from.getCoreFigure());
        assertEquals("Фигура должна переместится в поле", coreFigure, move.getTo().getCoreFigure());
    }

    @Test
    public void cancel() throws Exception {
        CoreFigure oldCoreFigure = new CoreFigure(TypeGamer.White);
        to.setCoreFigure(oldCoreFigure);
        move = new Move(from, to, coreFigure);
        move.make();
        move.cancel();
        assertEquals("На новом поле должна остаться старая фигура", oldCoreFigure, move.getTo().getCoreFigure());
        assertEquals("Фигура должна остаться в поле", coreFigure, move.getFrom().getCoreFigure());
    }

    @Test
    public void cancelVoid() throws Exception {
        move.cancel();
        assertNull("После отмены хода, новое поле должно очистится", to.getCoreFigure());
        assertEquals("Фигура должна остаться в поле", coreFigure, move.getFrom().getCoreFigure());
    }

    @Test
    public void isCharacteristicsMoveElephant() throws  Exception {
        CoreFigure elephant = new CoreFigure(TypeGamer.Black, TypeFigure.Elephant);
        to.setCoreFigure(elephant);
        move = new Move(from, to, coreFigure);
        assertTrue("Бьем слона", move.isCharacteristicsMove(CheckElephant));
        assertFalse("Не бьем короля", move.isCharacteristicsMove(CheckKing));
    }

    @Test
    public void isCharacteristicsMoveKing() throws  Exception {
        CoreFigure king = new CoreFigure(TypeGamer.Black, TypeFigure.King);
        to.setCoreFigure(king);
        move = new Move(from, to, coreFigure);
        assertTrue("Бьем короля", move.isCharacteristicsMove(CheckKing));
        assertFalse("Не бьем слона", move.isCharacteristicsMove(CheckElephant));

    }
    @Test
    public void isCharacteristicsMoveLeader() throws  Exception {
        CoreFigure leader = new CoreFigure(TypeGamer.Black, TypeFigure.Leader);
        to.setCoreFigure(leader);
        move = new Move(from, to, coreFigure);
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
        from.setCoreFigure(coreFigure);
        to.setCoreFigure(coreFigure);
        move = new Move(from, to, coreFigure, null);
        assertEquals("До хода поле заполенно", coreFigure, from.getCoreFigure());
        move.make();
        assertEquals("После хода поле заполенно", coreFigure, move.getFrom().getCoreFigure());
        assertNull("Куда ходили осталось пусто",  move.getTo().getCoreFigure());

    }
    @Test
    public void cancelShot() throws Exception {
        from.setCoreFigure(coreFigure);
        to.setCoreFigure(coreFigure);
        move = new Move(from, to, coreFigure, null);
        assertEquals("До хода поле заполенно", coreFigure, from.getCoreFigure());
        move.make();
        move.cancel();
        assertEquals("После хода поле заполенно", coreFigure, move.getFrom().getCoreFigure());
        assertEquals("Куда ходили осталось пусто", coreFigure, move.getTo().getCoreFigure());

    }



}