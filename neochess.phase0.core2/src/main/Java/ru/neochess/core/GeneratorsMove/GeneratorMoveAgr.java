package ru.neochess.core.GeneratorsMove;

import ru.neochess.core.*;
import ru.neochess.core.Move.Move;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by TiJi on 26.03.17.
 * генерация хода агра
 */
public class GeneratorMoveAgr  implements IGeneratorMove {

    @Override
    public List<Move> getMove(CellBoard currentCell, TypeGamer typeGamer) {
        List<Move> result = new ArrayList<>();

        Iterator<CellBoard> down = currentCell.getIterator(AdjacentCell.Down);
        Iterator<CellBoard> leftdown = currentCell.getIterator(AdjacentCell.LeftDown);
        Iterator<CellBoard> rightdown = currentCell.getIterator(AdjacentCell.RightDown);
        Iterator<CellBoard> up = currentCell.getIterator(AdjacentCell.Up);
         boolean firstStep = false;
         boolean firstStepFromLeader = false;


        //доп условия
        if (currentCell.getCell().getY() == 1) // ход с 9й линии
        {
            firstStep = true;

            CellBoard prev = up.next();
            Figure prevF = prev.getFigure();
          //  if (prev.getFigure().getTypeFigure().equals(TypeFigure.Leader)) // ход от вожака
            if (prevF  != null) // ход от вожака
            if (prevF.getTypeFigure().equals(TypeFigure.Leader))
                firstStepFromLeader = true;
        }


        //ход
        if (down.hasNext()) {
            CellBoard next = down.next();
            if (next.getFigure() == null) {
                result.add(new Move(currentCell, next,
                        currentCell.getFigure()));
            }

            if (firstStep) {
            down = next.getIterator(AdjacentCell.Down);


                if (down.hasNext()) {
                CellBoard next2 = down.next();
                if (next2.getFigure() == null) {
                    result.add(new Move(currentCell, next2,
                            currentCell.getFigure()));
                }
            }
           if( firstStepFromLeader)
           {
               CellBoard next3 = down.next();
               if (next3.getFigure() == null) {
                   result.add(new Move(currentCell, next3,
                           currentCell.getFigure()));
               }
           }

        }

        }

        //аттака
        if (down.hasNext()) {
            CellBoard next = down.next();
            if (next.getFigure() != null) {
                result.add(new Move(currentCell, next,
                        currentCell.getFigure()));
            }
        }

        if (leftdown.hasNext()) {
            CellBoard next = leftdown.next();
            if (next.getFigure() != null) {
                result.add(new Move(currentCell, next,
                        currentCell.getFigure()));
            }
        }
        if (rightdown.hasNext()) {
            CellBoard next = rightdown.next();
            if (next.getFigure() != null) {
                result.add(new Move(currentCell, next,
                        currentCell.getFigure()));
            }
        }
        return result;
    }

}
