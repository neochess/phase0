package ru.neochess.core.Move;

import ru.neochess.core.CellBoard;
import ru.neochess.core.CoreFigure;

/**
 * Created by diviz on 11.02.2017.
 */
public interface IMove {
    void make();

    void cancel();

    CellBoard getFrom();

    CellBoard getTo();

    CoreFigure getCoreFigureTo();

   Boolean isCharacteristicsMove(CharacteristicsMove characteristicsMove);
}
