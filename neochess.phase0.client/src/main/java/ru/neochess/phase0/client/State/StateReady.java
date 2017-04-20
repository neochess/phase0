package ru.neochess.phase0.client.State;
import ru.neochess.phase0.client.CheMessage.ChessMessage.*;

import ru.neochess.phase0.client.ChessBoard;
import ru.neochess.phase0.client.CheMessage.*;
import javax.swing.*;
import ru.neochess.core.*;



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
    public void sendMove(String board, String move) {

    }

    @Override
    public void receiveMove(ChessBoard chessboard, String line, String move) {

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
        wrapper.sessionData.gameID = msg.getSessionId();
        wrapper.chessBoard.addTextArea1(msg.getUser(0).getName() + " vs " + msg.getUser(1).getName());

        if (msg.getUser(0).getName().equals(UserName))
        {
            wrapper.sessionData.userID = msg.getUser(0).getId();
            wrapper.sessionData.userName = msg.getUser(0).getName();
            wrapper.sessionData.race = msg.getUser(0).getRace();

           wrapper.sessionData.enemyID = msg.getUser(1).getId();
           Enemy = wrapper.sessionData.enemyName = msg.getUser(1).getName();

        }

       else if (msg.getUser(1).getName().equals(UserName))
        {
            wrapper.sessionData.userID = msg.getUser(1).getId();
            wrapper.sessionData.userName = msg.getUser(1).getName();
            wrapper.sessionData.race = msg.getUser(1).getRace();

            wrapper.sessionData.enemyID = msg.getUser(0).getId();
            Enemy = wrapper.sessionData.enemyName = msg.getUser(0).getName();
        }

        if (wrapper.sessionData.race.equals("P"))
        {
            wrapper.setCurrent(new StateMove());
            System.out.println("I am P");
            wrapper.chessBoard.chessclient.setTitle("|NeoChess| " + UserName + " playing for people");
            JOptionPane.showMessageDialog(null, "Welcome to NeoChess! People is your race!", "Hi, " + UserName + ". Your enemy is " + Enemy, JOptionPane.PLAIN_MESSAGE);

        }
        else if (wrapper.sessionData.race.equals("A")) {
            wrapper.setCurrent(new StateWait());
            System.out.println("I am A");
            wrapper.chessBoard.chessclient.setTitle("|NeoChess| " + UserName + " playing for animals");
            JOptionPane.showMessageDialog(null, "Welcome to NeoChess! Your race is Animal!", "Hi, " + UserName + ". Your enemy is " + Enemy, JOptionPane.PLAIN_MESSAGE);

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
       // System.out.println(message);
        wrapper.sendToServer(message);

        wrapper.chessBoard.chessclient.setTitle("|NeoChess| " + UserName + " waiting for enemy...");

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
