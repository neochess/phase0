package ru.neochess.phase0.client.State;

import ru.neochess.phase0.client.ChessBoard;
import javax.swing.JOptionPane;

/**
 * Created by TiJi on 03.01.17.
 */

public class StateEnd extends State implements ClientState {
    public void process() {
        JOptionPane.showMessageDialog(null, "END OF GAME", "InfoBox: " + "GAME END", JOptionPane.INFORMATION_MESSAGE);
        wrapper.setCurrent(new StateReady());

    }


    @Override
    public void sendMove(String line) {

    }

    @Override
    public void receiveMove(ChessBoard chessboard, String line) {

    }

    @Override
    public void receiveState(String state) {
        switch (state) {
            case "ERROR":
                wrapper.setCurrent(new StateError());
                break;
        }

    }

    @Override
    public void sendState() {

        System.out.println("GameEND");

        wrapper.sendST("@GameEND");

        System.out.println("@@GameEND");

    }

    @Override
    public void recieveColor(char color) {

    }

    @Override
    public void finishGame() {

    }

}

