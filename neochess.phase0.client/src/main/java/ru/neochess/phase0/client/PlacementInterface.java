package ru.neochess.phase0.client;

import java.util.Map;

/**
 * Created by for on 03.11.16.
 */

@FunctionalInterface
public interface PlacementInterface {
    public Figure[][] operation(Figure[][] matrix, Map<String,Integer> xy, Figure f);
}
