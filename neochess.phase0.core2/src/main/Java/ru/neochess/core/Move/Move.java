package ru.neochess.core.Move;

import ru.neochess.core.CellBoard;
import ru.neochess.core.Figure;
import ru.neochess.core.TypeFigure;

/**
 * Ход
 * Created by diviz on 10.12.2016.
 */
public class Move implements IMove {

    protected final CellBoard from, to;
    private final Figure oldFigureFrom;
    private final Figure oldFigureTo;
    private final Figure figureTo;
    private final Figure figureFrom;

    public Move(CellBoard from, CellBoard to, Figure figureTo) {
        this(from, to, null, figureTo);
    }

    public Move(CellBoard from, CellBoard to, Figure figureFrom, Figure figureTo) {
        this.from = from;
        this.to = to;
        this.figureTo = figureTo;
        this.figureFrom = figureFrom;
        oldFigureTo = to.getFigure();
        oldFigureFrom = from.getFigure();

    }

    public Move() {
        this.from = null;
        this.to = null;
        this.figureTo = null;
        this.figureFrom = null;
        oldFigureTo = null;
        oldFigureFrom = null;

    }

    @Override
    public void make() {
        from.setFigure(figureFrom);
        to.setFigure(figureTo);
    }

    @Override
    public void cancel() {
        to.setFigure(oldFigureTo);
        from.setFigure(oldFigureFrom);
    }


    @Override
    public CellBoard getFrom() {
        return from;
    }

    @Override
    public CellBoard getTo() {
        return to;
    }

    public Figure getFigureTo() {
        return figureTo;
    }

    //TODO: Нарушаем принципы чистого кода. Вынести в отдельный интерфейс стратегию
    @Override
    public Boolean isCharacteristicsMove(CharacteristicsMove characteristicsMove) {
        if (oldFigureTo == null) {
            return false;
        }
        switch (characteristicsMove) {
            case CheckElephant:
                return oldFigureTo.getTypeFigure() == TypeFigure.Elephant;
            case CheckKing:
                return oldFigureTo.getTypeFigure() == TypeFigure.King
                        || oldFigureTo.getTypeFigure() == TypeFigure.Leader;
            default:
                return false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Move move = (Move) o;
        if (getFrom() != null ? !getFrom().equals(move.getFrom()) : move.getFrom() != null) return false;
        if (getTo() != null ? !getTo().equals(move.getTo()) : move.getTo() != null) return false;
        if (oldFigureFrom != null ? !oldFigureFrom.equals(move.oldFigureFrom) : move.oldFigureFrom != null)
            return false;
        return getFigureTo() != null ? getFigureTo().equals(move.getFigureTo()) : move.getFigureTo() == null;
    }

    @Override
    public int hashCode() {
        int result = getFrom() != null ? getFrom().hashCode() : 0;
        result = 31 * result + (getTo() != null ? getTo().hashCode() : 0);
        result = 31 * result + (oldFigureFrom != null ? oldFigureFrom.hashCode() : 0);
        result = 31 * result + (getFigureTo() != null ? getFigureTo().hashCode() : 0);

        return result;
    }

    @Override
    public String toString() {
        return from + "->" + to + ":" + figureTo;
    }
}
