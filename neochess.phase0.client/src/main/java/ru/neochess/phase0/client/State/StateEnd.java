package ru.neochess.phase0.client.State;

import ru.neochess.phase0.client.ChessBoard;

/**
 * Created by TiJi on 03.01.17.
 */

public class StateEnd extends State implements ClientState{
    @Override
    public void sendMove(String line) {

    }

    @Override
    public void receiveMove(ChessBoard chessboard, String line) {

    }

    @Override
    public void receiveState() {

    }

    @Override
    public void sendState() {

    }

    @Override
    public void recieveColor( char color) {

    }
}
