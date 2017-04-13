package ru.neochess.core.GeneratorsMove;

import ru.neochess.core.AdjacentCell;
import ru.neochess.core.CellBoard;
import ru.neochess.core.Move.Move;
import ru.neochess.core.TypeGamer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by TiJi on 11.04.17.
 */
public class GeneratorMoveOfficer implements IGeneratorMove {
    List<Move> result = new ArrayList<>();

    public List<Move> getMove(CellBoard currentCell, TypeGamer typeGamer) {
        CellBoard next;
        Move move;

        Iterator<CellBoard> leftdown = currentCell.getIterator(AdjacentCell.LeftDown);
        Iterator<CellBoard> rightdown = currentCell.getIterator(AdjacentCell.RightDown);
        Iterator<CellBoard> leftUp = currentCell.getIterator(AdjacentCell.LeftUp);
        Iterator<CellBoard> rightUp = currentCell.getIterator(AdjacentCell.RightUp);

        //диагонали
        //1.
        do { if (leftUp.hasNext()) {
            next = leftUp.next();
            move = createMove(currentCell, next);
            result.add(move);
        } else move = null;

        } while (move != null);

        //2.
            do {
                if (rightUp.hasNext()) {
                next = rightUp.next();
                move = createMove(currentCell, next);
                result.add(move);
                } else move = null;

            } while (move != null);

        //3.
        do {
            if (leftdown.hasNext()) {
            next = leftdown.next();
            move = createMove(currentCell, next);
            result.add(move);
            } else move = null;

        } while (move != null);

        //4.
        do {
            if (rightdown.hasNext()) {
                next = rightdown.next();
                move = createMove(currentCell, next);
                result.add(move);
            } else move = null;

        } while (move != null);


        return result;
    }

    protected Move createMove(CellBoard currentCell, CellBoard next) {

        if (next.getFigure() == null) {
            return (new Move(currentCell, next, currentCell.getFigure()));
        } else AttackMove(currentCell, next);

        return null;
    }

    protected void AttackMove(CellBoard currentCell, CellBoard next) {
        result.add(new Move(currentCell, next, currentCell.getFigure()));

    }
}
