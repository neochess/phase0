package ru.neochess.phase0.client;

import com.sun.corba.se.impl.orbutil.graph.Graph;


import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by for on 05.11.16.
 */
public class Board {

    //private ArrayList<BoardCell> cells = new ArrayList<>(); // все клетки доски
    private BoardCell cell_matrix[][] = new BoardCell[10][10];

    private ArrayList<Figure> figures= new ArrayList<>();

    public void Clear()
    {
        for (int i=0; i<10; i++){
            for(int j=0; j<10; j++){

                    this.cell_matrix[i][j].clear();

            }
        }

        figures.clear();
    }

    public Figure findFigureByCode (String code)
    {

       Figure f =  figures.stream().filter(figure1 -> code.equals(figure1.getCode())).findAny().orElse(null);

        return f;
    }

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

    public Figure putFigure (String pieceRace,String pieceState, String pieceCode, FiguresLibrary fl, Board board, Map<String,Integer> row_col)
    {
        Figure currentFigure = null;

        if (pieceCode.equals("H")) {

            //нужно как-то не добавлять слона в набор фигур 4 раза
            // проверяем есть ли уже слон ( а можно проверять есть ли незаполненый клетками слон

            currentFigure = figures.stream().filter(figure1 -> pieceCode.equals(figure1.getCode())).findAny().orElse(null);

        }
        if (currentFigure == null)
        {
            currentFigure = fl.getFigureByCode(pieceCode);
            figures.add(currentFigure);
            currentFigure.setState(pieceState);
            currentFigure.setRace(pieceRace);
        }

        currentFigure.placeOnBoard(board, row_col);
        return currentFigure;

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
        int cell = 50;
        int a = 0;

        for(Figure f : figures) {
            try {
                 gfx.drawImage(f.getImage(), f.getxCoord() + gap + 2, f.getyCoord() + gap + 2, observer);
            } catch (Exception ex) {
                System.out.println(ex.toString());
                System.out.println("Не нашел картинку для фигуры " + f.getDesc());
            }
        }
    }

}
