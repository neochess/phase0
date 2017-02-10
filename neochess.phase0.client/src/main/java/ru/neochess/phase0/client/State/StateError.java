package ru.neochess.phase0.client.State;

import ru.neochess.phase0.client.ChessBoard;
import javax.swing.JOptionPane;

/**
 * Created by TiJi on 03.12.16.
 */
public class StateError extends State  implements ClientState {

    public StateError () {
        JOptionPane.showMessageDialog(null, "END OF GAME", "InfoBox: " + "GAME ERROR", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void sendMove( String line) {

    }

    @Override
    public void receiveMove( ChessBoard chessboard, String line) {

    }

    @Override
    public void receiveState(String state) {
        switch (state)
        {
            case "END": wrapper.setCurrent(new StateEnd()); break;
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

    }
}
