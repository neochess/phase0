package ru.neochess.phase0.client;

import java.util.Map;

/**
 * Created by for on 03.11.16.
 */

@FunctionalInterface
public interface PlacementInterface {
    public Board operation(Board board, Map<String,Integer> row_col, Figure f);
}
