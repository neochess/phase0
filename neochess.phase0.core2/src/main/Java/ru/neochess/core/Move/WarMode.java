package ru.neochess.core.Move;

import ru.neochess.core.CellBoard;
import ru.neochess.core.Figure;

/**
 * Created by TiJi on 13.04.17.
 */
public class WarMode extends Move {
    private final CellBoard warCell;
    private final Figure warFigure;
   // private final Figure simpleFigure;


    public WarMode (CellBoard warCell, Figure warFigure)
    {

       this.warCell = warCell;
        this.warFigure = warFigure;
       // simpleFigure = warCell.getFigure();

    }
    @Override
    public void make() {

    }

    @Override
    public void cancel() {
        //aimCell.setFigure(aim);
    }

    @Override
    public CellBoard getFrom() {
        return null;
    }

    @Override
    public CellBoard getTo() {
        return null;
    }

    @Override
    public Figure getFigureTo() {
        return null;
    }

    @Override
    public Boolean isCharacteristicsMove(CharacteristicsMove characteristicsMove) {
        return null;
    }

    @Override
    public String toString() {
        return warFigure + ":" + warCell + ">>";
    }
}
