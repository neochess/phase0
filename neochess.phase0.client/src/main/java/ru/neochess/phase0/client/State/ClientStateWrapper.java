package ru.neochess.phase0.client.State;

import ru.neochess.phase0.client.ChessBoard;
import ru.neochess.phase0.client.ChessClient;
import ru.neochess.phase0.client.ChessServerConnection;

/**
 * Created by TiJi on 04.01.17.
 */
public class ClientStateWrapper {
    public ClientState currentState;
    public ChessBoard chessBoard;

    ChessServerConnection serverconnection;


    public char myRace;

    public ClientStateWrapper(ChessBoard c) {

        setCurrent(new StateReady());
        chessBoard = c;
        serverconnection = new ChessServerConnection(chessBoard , this);
    }

    public void setCurrent( ClientState s ) { currentState = s;
        currentState.setWrapper(this);

    }
    public ClientState getCurrent( ) { return  currentState;}

    synchronized public void processMSG(String line) {

        if (line.charAt(0) == '@') {

            processCommand(chessBoard.chessclient, line);
            return;
        }

        currentState.receiveMove( chessBoard, line);
       // chessBoard.decodeBoard(line);
      //  chessBoard.repaint();

    }

    public void processCommand(ChessClient chessclient, String command){
//пока так -  исправить!!!
        if (command.compareTo("@BLACK") == 0) {

            myRace = 'B';
            System.out.println("I am B");
            chessclient.setTitle("NeoChess - B");

        } else if (command.compareTo("@WHITE") == 0) {
            System.out.println("I am W");

            myRace = 'W';
            chessclient.setTitle("NeoChess - W");
            //chessBoard.resetBoard();

        }
        currentState.recieveColor(myRace);

    }

    public void sendMSG (String line) {
        try {
            serverconnection.send(line);
            serverconnection.send("@TOKEN");
        } catch (Exception ex) {

        }
    }
}