package ru.neochess.phase0.client.MoveHandler;
import ru.neochess.phase0.client.ChessBoard;
import ru.neochess.phase0.client.Figure;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by TiJi on 05.02.17.
 */
public class MoveHandler {

    int grabbed_piece, from_row, from_col, to_row, to_col;


    public boolean figureTransform(int X, int Y, ChessBoard chessBoard) {


        Map<String, Integer> row_col = new HashMap();

        Figure selectFigure;
        Figure newFigure;

        from_row = (Y - chessBoard.gap) / chessBoard.cellsize;
        from_col = (X - chessBoard.gap) / chessBoard.cellsize;

        if (from_row < 0 || from_row > 9) return false;
        if (from_col < 0 || from_col > 9) return false;


        selectFigure = chessBoard.board.getCellByIndex(from_row, from_col).getFigure();

        //обычная пешка
        if (selectFigure != null &&
                selectFigure.getCode().compareTo("O") == 0) {

            newFigure = chessBoard.fl.getFigureByCode("R"); //Боевая пешка
            newFigure.setState(selectFigure.getState());
            newFigure.setRace(selectFigure.getRace());

            row_col.put("row", from_row);
            row_col.put("col", from_col);
            newFigure.placeOnBoard(chessBoard.board, row_col);
            chessBoard.board.saveFigure(newFigure);

            chessBoard.board.removeFigure(selectFigure);

            selectFigure = null;
            newFigure.printCells();
            chessBoard.move = newFigure.printNotation();
            chessBoard.addTextArea1(newFigure.printNotation());
            newFigure = null;

            //myTurn = false; // изменнение пешки считается ходом.

        return true;}
        else {return false; }

    }
    public boolean moveBegin(int X, int Y, ChessBoard chessBoard)
    {
        from_row = (Y - chessBoard.gap) / chessBoard.cellsize;
        from_col = (X - chessBoard.gap) / chessBoard.cellsize;

        if (from_row < 0 || from_row > 9) return false;
        if (from_col < 0 || from_col > 9) return false;

        chessBoard.grabbed_figure = chessBoard.board.getCellByIndex(from_row, from_col).getFigure();

        chessBoard.grabbed_figure.setMousePos(chessBoard.board.getCellByIndex(from_row, from_col));

        if(chessBoard.grabbed_figure==null) return false;

       /* if (grabbed_figure.getCode().equals("H"))
        {
            from_row = grabbed_figure.getRow();
            from_col =  grabbed_figure.getCol();
        }*/
       // chessBoard.grabbed_figure.removeFromCell(chessBoard.board.getCellByIndex(from_row, from_col));

        chessBoard.board.removeFigure(chessBoard.grabbed_figure);

        return true;
    }

    public boolean moveEnd (int X, int Y, ChessBoard chessBoard)
    {
        Map<String,Integer> row_col = new HashMap();

        if(chessBoard.grabbed_figure == null) return false;

        to_row = (Y - chessBoard.gap) / chessBoard.cellsize;
        to_col = (X - chessBoard.gap) / chessBoard.cellsize;

        if (to_row < 0 || to_row > 9 || to_col < 0 || to_col > 9) {
//
            row_col.put("row", from_row);
            row_col.put("col", from_col);
           // chessBoard.addTextArea1(chessBoard.grabbed_figure.printNotation());
            chessBoard.grabbed_figure.placeOnBoard(chessBoard.board, row_col);
           // chessBoard.addTextArea1(chessBoard.grabbed_figure.printNotation());

            chessBoard.grabbed_figure = null;

            return false;
        }

        if (from_row == to_row && from_col == to_col)

         {
             chessBoard.board.saveFigure(chessBoard.grabbed_figure);

            row_col.put("row", from_row);
            row_col.put("col", from_col);
             chessBoard.grabbed_figure.placeOnBoard(chessBoard.board, row_col);

             chessBoard.grabbed_figure = null;
             chessBoard.repaint();
            return false;
        }

            chessBoard.board.saveFigure(chessBoard.grabbed_figure);
            //board.getCellByIndex(to_row, to_col).placeIn(grabbed_figure);

            row_col.put("row", to_row);
            row_col.put("col", to_col);

      //  chessBoard.addTextArea1(chessBoard.grabbed_figure.printNotation());
            chessBoard.grabbed_figure.placeOnBoard(chessBoard.board, row_col);

        chessBoard.grabbed_figure.printCells();
        chessBoard.move = chessBoard.grabbed_figure.printNotation();
        chessBoard.addTextArea1(chessBoard.grabbed_figure.printNotation());

        chessBoard.grabbed_figure = null;
     return true;
    }

}
