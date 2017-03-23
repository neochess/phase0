package ru.neochess.phase0.client.State;


import ru.neochess.phase0.client.ChessBoard;
import javax.swing.JOptionPane;
import ru.neochess.phase0.client.CheMessage.ChessMessage.*;

/**
 * Created by TiJi on 03.01.17.
 */

public class StateEnd extends State implements ClientState {

    public StateEnd(ClientStateWrapper wrapper ) {
        JOptionPane.showMessageDialog(null, "END OF GAME", "InfoBox: " + "GAME END", JOptionPane.INFORMATION_MESSAGE);

    }

    public StateEnd() {
        JOptionPane.showMessageDialog(null, "END OF GAME", "InfoBox: " + "GAME END", JOptionPane.INFORMATION_MESSAGE);
       // wrapper.chessBoard.addTextArea1("GAME END");
    }

    @Override
    public void sendMove(String line, String move) {

    }

    @Override
    public void receiveMove(ChessBoard chessboard, String line, String move) {

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

        User.Builder user = User.newBuilder();
        user.setId(wrapper.sessionData.userID);

        NeoCheMessage.Builder messageBuilder =NeoCheMessage.newBuilder();
        messageBuilder.addUser(user);
        messageBuilder.setState("end");
        messageBuilder.setSessionId(wrapper.sessionData.gameID);
        NeoCheMessage message =  messageBuilder.build();
        // System.out.println(message);
        wrapper.sendToServer(message);

       // wrapper.sendST("@GameEND");

       // System.out.println("@@GameEND");

        wrapper.chessBoard.setInitialBoard();

    }

    @Override
    public void receiveConfirm(NeoCheMessage msg) {

    }

    @Override
    public void recieveColor(char color) {

    }

    @Override
    public void finishGame() {

    }

}

