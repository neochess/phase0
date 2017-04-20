package ru.neochess.core.Move;

import ru.neochess.core.CellBoard;
import ru.neochess.core.CoreFigure;

/**
 * Created by TiJi on 12.04.17.
 */
public class Shot extends Move{

    private final CellBoard killerCell, aimCell;
    private final CoreFigure killer;
    private final CoreFigure aim;


    public Shot (CellBoard killerCell, CellBoard aimCell, CoreFigure killer, CoreFigure aim)
    {
        this.aim = aim;
        this.killer = killer;
        this.killerCell = killerCell;
        this.aimCell = aimCell;

    }


    @Override
    public void make() {
        aimCell.setCoreFigure(null);

    }

    @Override
    public void cancel() {
        aimCell.setCoreFigure(aim);
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
        return killer + ":" + killerCell + "->>" + aim + ":" + aimCell;
    }
}
