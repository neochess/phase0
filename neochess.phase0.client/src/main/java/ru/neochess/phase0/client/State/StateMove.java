package ru.neochess.phase0.client.State;


import ru.neochess.phase0.client.ChessBoard;

import javax.swing.*;

/**
 * Created by TiJi on 03.12.16.
 */
public class StateMove extends State  implements ClientState {
    public void process() {

    }

    @Override
    public void sendMove(String board) {
        wrapper.sendMSG(board);
        wrapper.setCurrent(new StateWait());
    }

    @Override
    public void receiveMove(ChessBoard chessboard, String line) {

    }

    @Override
    public void receiveState(String state) {
        switch (state)
        {
            case "ERROR": wrapper.setCurrent(new StateError()); break;
            case "END": finishGame(); break;
        }


    }

    @Override
    public void sendState() {

    }

    @Override
    public void recieveColor( char color) {

    }

    @Override
    public void finishGame() {
        wrapper.setCurrent(new StateEnd());
    }
}
