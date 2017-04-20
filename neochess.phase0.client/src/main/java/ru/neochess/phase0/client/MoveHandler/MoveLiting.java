package ru.neochess.phase0.client.MoveHandler;

//import ru.neochess.core.Board;
import ru.neochess.core.*;
import ru.neochess.core.Move.Move;
import ru.neochess.phase0.client.ChessBoard;
import ru.neochess.phase0.client.Figure;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by TiJi on 15.04.17.
 */
public class MoveLiting {

    java.util.List<Move> moveList = new ArrayList<>();
    MoveLiting(ChessBoard chessboard, int x, int y)
    {
        ru.neochess.phase0.client.Figure figure = chessboard.grabbed_figure;

        moveList = figure.getMoveGenerator(x, y);
        chessboard.setMoveList(moveList);

    }


}
