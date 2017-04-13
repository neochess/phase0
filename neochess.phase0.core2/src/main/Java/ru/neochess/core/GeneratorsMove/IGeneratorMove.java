package ru.neochess.core.GeneratorsMove;

import ru.neochess.core.CellBoard;
import ru.neochess.core.Move.Move;
import ru.neochess.core.TypeGamer;

import java.util.List;

/**
 * Стратегия для генерации ходов
 * Created by diviz on 10.12.2016.
 */
@FunctionalInterface
public interface IGeneratorMove {
     List<Move> getMove(CellBoard currentCell, TypeGamer typeGamer);

}
