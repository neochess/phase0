package ru.neochess.core.Move;

import ru.neochess.core.CellBoard;
import ru.neochess.core.Figure;

/**
 * Created by TiJi on 12.04.17.
 */
public class Shot extends Move{

    private final CellBoard killerCell, aimCell;
    private final Figure killer;
    private final Figure aim;


    public Shot (CellBoard killerCell, CellBoard aimCell, Figure killer, Figure aim)
    {
        this.aim = aim;
        this.killer = killer;
        this.killerCell = killerCell;
        this.aimCell = aimCell;

    }


    @Override
    public void make() {
        aimCell.setFigure(null);

    }

    @Override
    public void cancel() {
        aimCell.setFigure(aim);
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
        return killer + ":" + killerCell + "->>" + aim + ":" + aimCell;
    }
}
