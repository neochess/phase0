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
 * Created by TiJi on 11.04.17.
 */
public class GeneratorMoveArcher implements IGeneratorMove {

    List<Move> result = new ArrayList<>();
   // List<Shot> result1 = new ArrayList<>();

    public List<Move> getMove(CellBoard currentCell, TypeGamer typeGamer) {
        CellBoard next;
        CellBoard nextS;
        Move move;
        Integer shotDist = 3;
        Integer shotSteps;

        //дикообраз

        Iterator<CellBoard> up = currentCell.getIterator(AdjacentCell.Up);
        Iterator<CellBoard> down = currentCell.getIterator(AdjacentCell.Down);
        Iterator<CellBoard> right = currentCell.getIterator(AdjacentCell.Right);
        Iterator<CellBoard> left = currentCell.getIterator(AdjacentCell.Left);

        Iterator<CellBoard> upS = currentCell.getIterator(AdjacentCell.Up);
        Iterator<CellBoard> downS = currentCell.getIterator(AdjacentCell.Down);
        Iterator<CellBoard> rightS = currentCell.getIterator(AdjacentCell.Right);
        Iterator<CellBoard> leftS = currentCell.getIterator(AdjacentCell.Left);
        //Iterator<CellBoard> leftdown = currentCell.getIterator(AdjacentCell.LeftDown);
        //Iterator<CellBoard> rightdown = currentCell.getIterator(AdjacentCell.RightDown);
        //Iterator<CellBoard> leftUp = currentCell.getIterator(AdjacentCell.LeftUp);
        //Iterator<CellBoard> rightUp = currentCell.getIterator(AdjacentCell.RightUp);

        // движение вверх, вниз, вперед назад на одну клетку
        if (down.hasNext()) {
            next = down.next();
            //   if (next.getFigure() == null) {
            result.add(new Move(currentCell, next,
                    currentCell.getCoreFigure()));
            //  }
        }

        if (up.hasNext()) {
            next = up.next();
            //  if (next.getFigure() == null) {
            result.add(new Move(currentCell, next,
                    currentCell.getCoreFigure()));
            //   }
        }

        if (left.hasNext()) {
            next = left.next();
            //   if (next.getFigure() == null) {
            result.add(new Move(currentCell, next,
                    currentCell.getCoreFigure()));
            //  }
        }

        if (right.hasNext()) {
            next = right.next();
            //  if (next.getFigure() == null) {
            result.add(new Move(currentCell, next,
                    currentCell.getCoreFigure()));
            // }
        }

        //выстрелы по вертикалям и горизонталям

        // горизонтали вертикали

        //1.
        shotSteps = 1;
        do { if (upS.hasNext()) {
            nextS = upS.next();
            move = createMove(currentCell, nextS);
           // result.add(move);
        } else move = null;

        } while ((move != null)&&(shotSteps++ <= shotDist));

        //2.
        shotSteps = 1;
        do {
            if (rightS.hasNext()) {
                nextS = rightS.next();
                move = createMove(currentCell, nextS);
               // result.add(move);
            } else move = null;

        } while ((move != null)&&(shotSteps++ <= shotDist));

        //3.
        shotSteps = 1;
        do {
            if (downS.hasNext()) {
                nextS = downS.next();
                move = createMove(currentCell, nextS);
               // result.add(move);
            } else move = null;

        } while ((move != null)&&(shotSteps++ <= shotDist));

        //4.
        shotSteps = 1;
        do {
            if (leftS.hasNext()) {
                nextS = leftS.next();
                move = createMove(currentCell, nextS);
              //  result.add(move);
            } else move = null;

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
