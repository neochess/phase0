package ru.neochess.phase0.client.State;
import ru.neochess.phase0.client.CheMessage.ChessMessage.*;

import ru.neochess.phase0.client.ChessBoard;

/**
 * Created by TiJi on 03.12.16.
 */
public class StateWait extends State  implements ClientState {

    @Override
    public void sendMove(String line, String move) {

    }

    @Override
    public void receiveMove(ChessBoard chessboard, String line, String move) {
        chessboard.addTextArea1(move);
        chessboard.decodeBoard(line);
        chessboard.repaint();
        wrapper.setCurrent(new StateMove());
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
    public void receiveConfirm(NeoCheMessage msg) {

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
