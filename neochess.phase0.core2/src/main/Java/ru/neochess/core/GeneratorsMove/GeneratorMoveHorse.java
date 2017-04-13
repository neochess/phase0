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
public class GeneratorMoveHorse implements IGeneratorMove {

    List<Move> result = new ArrayList<>();

    @Override
    public List<Move> getMove(CellBoard currentCell, TypeGamer typeGamer) {
        CellBoard next;
        CellBoard next2;
        //CellBoard next3;
       // Move move;

        Iterator<CellBoard> up = currentCell.getIterator(AdjacentCell.Up);
        Iterator<CellBoard> down = currentCell.getIterator(AdjacentCell.Down);
        Iterator<CellBoard> left = currentCell.getIterator(AdjacentCell.Left);
        Iterator<CellBoard> right = currentCell.getIterator(AdjacentCell.Right);
        Iterator<CellBoard> leftdown = currentCell.getIterator(AdjacentCell.LeftDown);
        Iterator<CellBoard> rightdown = currentCell.getIterator(AdjacentCell.RightDown);
        Iterator<CellBoard> leftUp = currentCell.getIterator(AdjacentCell.LeftUp);
        Iterator<CellBoard> rightUp = currentCell.getIterator(AdjacentCell.RightUp);

        if (down.hasNext()) {
            next = down.next();

                leftdown = next.getIterator(AdjacentCell.LeftDown);
                if (leftdown.hasNext()) {
                    next2 = leftdown.next();
                        result.add(new Move(currentCell, next2, currentCell.getFigure()));
                    }
                rightdown = next.getIterator(AdjacentCell.RightDown);
                if (rightdown.hasNext()) {
                    next2 = rightdown.next();
                    result.add(new Move(currentCell, next2, currentCell.getFigure()));

            }
        }

        if (up.hasNext()) {
            next = up.next();


                leftUp = next.getIterator(AdjacentCell.LeftUp);
                if (leftdown.hasNext()) {
                    next2 = leftUp.next();
                    result.add(new Move(currentCell, next2, currentCell.getFigure()));
                }
                rightUp = next.getIterator(AdjacentCell.RightUp);
                if (rightdown.hasNext()) {
                    next2 = rightUp.next();
                    result.add(new Move(currentCell, next2, currentCell.getFigure()));
                }
            }

        if (right.hasNext()) {
            next = right.next();

            rightUp = next.getIterator(AdjacentCell.RightUp);
            if (rightUp.hasNext()) {
                next2 = rightUp.next();
                result.add(new Move(currentCell, next2, currentCell.getFigure()));
            }
            rightdown = next.getIterator(AdjacentCell.RightDown);
            if (rightdown.hasNext()) {
                next2 = rightdown.next();
                result.add(new Move(currentCell, next2, currentCell.getFigure()));
            }
        }

        if (left.hasNext()) {
            next = left.next();

            leftUp = next.getIterator(AdjacentCell.LeftUp);
            if (leftUp.hasNext()) {
                next2 = leftUp.next();
                result.add(new Move(currentCell, next2, currentCell.getFigure()));
            }
            leftdown = next.getIterator(AdjacentCell.LeftDown);
            if (rightdown.hasNext()) {
                next2 = leftdown.next();
                result.add(new Move(currentCell, next2, currentCell.getFigure()));
            }
        }



        return result;
    }
}
