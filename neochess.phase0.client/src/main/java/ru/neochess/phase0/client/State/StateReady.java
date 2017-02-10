package ru.neochess.phase0.client.State;

import ru.neochess.phase0.client.ChessBoard;

import javax.swing.*;
import java.sql.Wrapper;

/**
 * Created by TiJi on 03.12.16.
 */
public class StateReady extends State  implements ClientState {


    public void process() {

       if (wrapper.sessionData.userName.isEmpty()) {
           wrapper.sessionData.userName = JOptionPane.showInputDialog("UserName");
        }

        wrapper.chessBoard.setInitialBoard();
    }

    @Override
    public void sendMove(String board) {

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
        if (color == 'W') {
            wrapper.setCurrent(new StateMove());
            System.out.println("I am W");
            wrapper.chessBoard.chessclient.setTitle("NeoChess. " + wrapper.sessionData.userName + " playing for people");
            JOptionPane.showMessageDialog(null, "People is your race!", "Hi, " + wrapper.sessionData.userName + "! Welcome to NeoChess", JOptionPane.PLAIN_MESSAGE);

        } else if (color == 'B') {
            wrapper.setCurrent(new StateWait());
            System.out.println("I am B");
            wrapper.chessBoard.chessclient.setTitle("NeoChess. " + wrapper.sessionData.userName + " playing for animals");
            JOptionPane.showMessageDialog(null, "Your race is Animal!", "Hi, " + wrapper.sessionData.userName + "! Welcome to NeoChess", JOptionPane.PLAIN_MESSAGE);

        }
    }

        @Override
        public void finishGame() {
            wrapper.setCurrent(new StateEnd());

        }



}
