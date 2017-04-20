package ru.neochess.core.GeneratorsMove;

import ru.neochess.core.AdjacentCell;
import ru.neochess.core.CellBoard;
import ru.neochess.core.Move.Move;
import ru.neochess.core.Move.Shot;
import ru.neochess.core.TypeGamer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Генерация аттаки пешки
 * Created by diviz on 05.01.2017.
 */
public class GeneratorMovePawnAttack implements IGeneratorMove {

    List<Move> result = new ArrayList<>();
    @Override
    public List<Move> getMove(CellBoard currentCell, TypeGamer typeGamer) {

        CellBoard next;
        Move move;
        Integer shotDist = 1;
        Integer shotSteps;

        Iterator<CellBoard> leftUp = currentCell.getIterator(AdjacentCell.LeftUp);
        Iterator<CellBoard> rightUp = currentCell.getIterator(AdjacentCell.RightUp);
        Iterator<CellBoard> iterator = currentCell.getIterator(AdjacentCell.Up);

       //ход
        if (iterator.hasNext()) {
            next = iterator.next();
            if (next.getCoreFigure() == null) {
                result.add(new Move(currentCell, next,
                        currentCell.getCoreFigure()));
            }
        }

        //стреляем по диагонялям

        shotSteps = 1;
       do { if (leftUp.hasNext()) {
            next = leftUp.next();
            move = createMove(currentCell, next);

        }else move = null;

    } while ((move != null)&&(shotSteps++ <= shotDist));

        shotSteps = 1;
        do {
        if (rightUp.hasNext()) {
             next = rightUp.next();
            move = createMove(currentCell, next);
        }else move = null;

        } while ((move != null)&&(shotSteps++ <= shotDist));

        return result;

    }

    protected Move createMove(CellBoard currentCell, CellBoard next) {

        if (next.getCoreFigure() == null) {
            return (new Move(currentCell, next, currentCell.getCoreFigure()));
        }
        else if (next.getCoreFigure() != null)
            AttackMove(currentCell, next);

        return null;
    }

    protected void AttackMove(CellBoard currentCell, CellBoard next) {
        result.add(new Shot(currentCell, next, currentCell.getCoreFigure(), next.getCoreFigure()));

    }
}
