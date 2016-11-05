package ru.neochess.phase0.client;

/**
 * Created by for on 05.11.16.
 */
public class BoardCell {
    private int row, col;
    private Figure figure;

    public BoardCell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void clear() {
        if(this.figure != null) {
            this.figure.removeFromCell(this);
        }
        this.figure = null;
    }

    // вызывать только из FiguresLibrary
    // в остальных случаях фигура ставится на поле через Figure.placeOnBoard()
    public void placeIn(Figure fig) {
        // фигура, которая уже находится в этой клетке, будет побита
        if(this.figure!=null){
            this.figure.beaten();
        }

        fig.intoCell(this);
        this.figure = fig;
    }

    public Figure getFigure() {
        return figure;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
