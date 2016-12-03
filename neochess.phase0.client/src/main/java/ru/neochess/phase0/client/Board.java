package ru.neochess.phase0.client;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by for on 05.11.16.
 */
public class Board {

    //private ArrayList<BoardCell> cells = new ArrayList<>(); // все клетки доски
    private BoardCell cell_matrix[][] = new BoardCell[10][10];

    private ArrayList<Figure> figures= new ArrayList<>();

    public Board() {
        for (int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                this.cell_matrix[i][j] = new BoardCell(i, j);
            }
        }
    }


    public BoardCell getCellByIndex(int row, int col) {
        return this.cell_matrix[row][col];
    }

    public void saveFigure(Figure f){
        if (!figures.contains(f)) {
            figures.add(f);
        }
    }

    public void removeFigure(Figure f){
        if(figures.contains(f)){
            figures.remove(f);
        }

        // удалить также из всех клеток
        for (int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                if (this.cell_matrix[i][j].getFigure() == f) {
                    this.cell_matrix[i][j].clear();
                }
            }
        }
    }

    public void paintFigures(Graphics2D gfx, ChessBoard observer) {
        int gap = 50;
        for(Figure f : figures) {

            try {
                gfx.drawImage(f.getImage(), f.getxCoord() + gap + 2, f.getyCoord() + gap + 2, observer);
            } catch (Exception ex) {
                System.out.println("Не нашел картинку для фигуры " + f.getDesc());
            }
        }
    }

}
