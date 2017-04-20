package ru.neochess.core.GeneratorsMove;

import ru.neochess.core.AdjacentCell;
import ru.neochess.core.CellBoard;
import ru.neochess.core.Move.Move;
import ru.neochess.core.Move.WarMode;
import ru.neochess.core.TypeGamer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Ход пешки вперед
 * Created by diviz on 10.12.2016.
 */
public class GeneratorMovePawn implements IGeneratorMove {

    List<Move> result = new ArrayList<>();

    @Override
    public List<Move> getMove(CellBoard currentCell, TypeGamer typeGamer) {
        CellBoard next;
        Move move;

        boolean firstStep = false;
        Iterator<CellBoard> iterator = currentCell.getIterator(AdjacentCell.Up);

        //доп условия
        if (currentCell.getCell().getY() == 8) // ход с 2й линии
        {
            firstStep = true;
        }
        //ход
        if (iterator.hasNext()) {
            next = iterator.next();
            if (next.getCoreFigure() == null) {
                result.add(new Move(currentCell, next,
                        currentCell.getCoreFigure()));
            }

            if (firstStep) {
                iterator = next.getIterator(AdjacentCell.Up);


                if (iterator.hasNext()) {
                    CellBoard next2 = iterator.next();
                    if (next2.getCoreFigure() == null) {
                        result.add(new Move(currentCell, next2,
                                currentCell.getCoreFigure()));
                    }
                }
            }
        }

        //аттака отбычная (рубим пешкой)

        Iterator<CellBoard> leftUp = currentCell.getIterator(AdjacentCell.LeftUp);
        Iterator<CellBoard> rightUp = currentCell.getIterator(AdjacentCell.RightUp);

        if (leftUp.hasNext()) {
            next = leftUp.next();
            move = createMove(currentCell, next);
            if (move != null) {
                result.add(move);
            }
        }

        if (rightUp.hasNext()) {
            next = rightUp.next();
            move = createMove(currentCell, next);
            if (move != null) {
                result.add(move);
            }
        }

        //превращение пешки

        result.add(new WarMode(currentCell, currentCell.getCoreFigure()));

        return result;

    }

    protected Move createMove(CellBoard currentCell, CellBoard next) {

        if (next.getCoreFigure() != null) {
            return (new Move(currentCell, next, currentCell.getCoreFigure()));
        }

        return null;
    }



}
