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

        switch (command){
            case "@BLACK":
                myRace = 'B';
                currentState.recieveColor(myRace);
                break;
            case "@WHITE":
                myRace = 'W';
                currentState.recieveColor(myRace);
                break;
            case "@ERROR":
                currentState.receiveState("ERROR");
                break;
            case "@GameEND":
                currentState.receiveState("END");
                chessBoard.setInitialBoard();
                break;

        }

    }

    public void sendMSG (String line) {
        try {
            serverconnection.send(line);
            serverconnection.send("@TOKEN");
        } catch (Exception ex) {

        }
    }

    public void sendST (String line) {
        try {
           serverconnection.send(line);
        } catch (Exception ex) {

        }
    }


}