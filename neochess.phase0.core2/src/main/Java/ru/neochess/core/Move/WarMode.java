package ru.neochess.core.Move;

import ru.neochess.core.CellBoard;
import ru.neochess.core.CoreFigure;

/**
 * Created by TiJi on 13.04.17.
 */
public class WarMode extends Move {
    private final CellBoard warCell;
    private final CoreFigure warCoreFigure;
   // private final Figure simpleFigure;


    public WarMode (CellBoard warCell, CoreFigure warCoreFigure)
    {

       this.warCell = warCell;
        this.warCoreFigure = warCoreFigure;
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
    public CoreFigure getCoreFigureTo() {
        return null;
    }

    @Override
    public Boolean isCharacteristicsMove(CharacteristicsMove characteristicsMove) {
        return null;
    }

    @Override
    public String toString() {
        return warCoreFigure + ":" + warCell + ">>";
    }
}
