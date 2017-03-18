package ru.neochess.phase0.client.State;

import ru.neochess.phase0.client.ChessBoard;
import ru.neochess.phase0.client.CheMessage.*;
import javax.swing.*;


/**
 * Created by TiJi on 03.12.16.
 */
public class StateReady extends State  implements ClientState {
   // private String UserName;

    public StateReady() {

       if (UserName.isEmpty()) {
            UserName = JOptionPane.showInputDialog("UserName");
        }

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

     //  wrapper.sessionData.userName = UserName;

      ChessMessage.User.Builder user = ChessMessage.User.newBuilder();
        user.setName(UserName);

        ChessMessage.NeoCheMessage.Builder messageBuilder = ChessMessage.NeoCheMessage.newBuilder();
        messageBuilder.addUser(user);
        messageBuilder.setState("ready");
        ChessMessage.NeoCheMessage message =  messageBuilder.build();
        System.out.println(message);
        wrapper.sendToServer(message);


    }

    @Override
    public void recieveColor( char color) {
    /*    if (color == 'W') {
        //    wrapper.setCurrent(new StateMove());
            System.out.println("I am W");
            wrapper.chessBoard.chessclient.setTitle("NeoChess. " + UserName + " playing for people");
            JOptionPane.showMessageDialog(null, "People is your race!", "Hi, " + UserName + "! Welcome to NeoChess", JOptionPane.PLAIN_MESSAGE);

        } else if (color == 'B') {
          //  wrapper.setCurrent(new StateWait());
            System.out.println("I am B");
            wrapper.chessBoard.chessclient.setTitle("NeoChess. " + UserName + " playing for animals");
            JOptionPane.showMessageDialog(null, "Your race is Animal!", "Hi, " + UserName + "! Welcome to NeoChess", JOptionPane.PLAIN_MESSAGE);

        }*/
    }

        @Override
        public void finishGame() {
            wrapper.setCurrent(new StateEnd());

        }



}
