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
public class GeneratorMoveRinoceros implements IGeneratorMove {

    List<Move> result = new ArrayList<>();

    public List<Move> getMove(CellBoard currentCell, TypeGamer typeGamer) {
        CellBoard next;
        Move move;

        Iterator<CellBoard> up = currentCell.getIterator(AdjacentCell.Up);
        Iterator<CellBoard> down = currentCell.getIterator(AdjacentCell.Down);
        Iterator<CellBoard> right = currentCell.getIterator(AdjacentCell.Right);
        Iterator<CellBoard> left = currentCell.getIterator(AdjacentCell.Left);
       // Iterator<CellBoard> leftdown = currentCell.getIterator(AdjacentCell.LeftDown);
       // Iterator<CellBoard> rightdown = currentCell.getIterator(AdjacentCell.RightDown);
       // Iterator<CellBoard> leftUp = currentCell.getIterator(AdjacentCell.LeftUp);
       // Iterator<CellBoard> rightUp = currentCell.getIterator(AdjacentCell.RightUp);


        // горизонтали
        //1.
        do { if (up.hasNext()) {
            next = up.next();
            move = createMove(currentCell, next, up);
            result.add(move);
        } else move = null;

        } while (move != null);

        //2.
        do {
            if (right.hasNext()) {
                next = right.next();
                move = createMove(currentCell, next, right);
                result.add(move);
            } else move = null;

        } while (move != null);

        //3.
        do {
            if (down.hasNext()) {
                next = down.next();
                move = createMove(currentCell, next, down);
                result.add(move);
            } else move = null;

        } while (move != null);

        //4.
        do {
            if (left.hasNext()) {
                next = left.next();
                move = createMove(currentCell, next, left);
                result.add(move);
            } else move = null;

        } while (move != null);

        return result;
    }

    protected Move createMove(CellBoard currentCell, CellBoard next, Iterator<CellBoard> it) {

        if (next.getCoreFigure() == null) {
            return (new Move(currentCell, next, currentCell.getCoreFigure()));
        } else AttackMove(currentCell, next, it);

        return null;
    }

    protected void AttackMove(CellBoard currentCell, CellBoard next, Iterator<CellBoard> it) {
        if (it.hasNext())
        next = it.next();

        result.add(new Move(currentCell, next, currentCell.getCoreFigure()));

    }
}