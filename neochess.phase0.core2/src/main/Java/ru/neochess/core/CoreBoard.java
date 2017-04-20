package ru.neochess.core;

import java.util.ArrayList;

/**
 * Created by diviz on 10.12.2016.
 */
public class CoreBoard {

    //private ArrayList<BoardCell> cells = new ArrayList<>(); // все клетки доски
    private CellBoard cell_matrix[][] = new CellBoard[10][10];

    private ArrayList<CoreFigure> coreFigures = new ArrayList<>();

    public CellBoard getCellByIndex(int row, int col) {
        return this.cell_matrix[row][col];
    }

    public CoreBoard() {
        for (int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                this.cell_matrix[i][j] = new CellBoard(i, j, this);
            }
        }
    }

}
