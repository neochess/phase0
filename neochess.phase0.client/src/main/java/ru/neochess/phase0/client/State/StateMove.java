package ru.neochess.phase0.client.State;


import ru.neochess.phase0.client.CheMessage.*;
import ru.neochess.phase0.client.ChessBoard;

/**
 * Created by TiJi on 03.12.16.
 */
public class StateMove extends State  implements ClientState {

    @Override
    public void sendMove(String board, String move) {

     //   wrapper.sendMSG(board);
        ChessMessage.User.Builder user = ChessMessage.User.newBuilder();
        user.setId(wrapper.sessionData.userID);

        ChessMessage.NeoCheMessage.Builder messageBuilder = ChessMessage.NeoCheMessage.newBuilder();
        messageBuilder.addUser(user);
        messageBuilder.setState("move");
        messageBuilder.setSessionId(wrapper.sessionData.gameID);
        messageBuilder.setBoard(board);
        messageBuilder.setMove(move);
        ChessMessage.NeoCheMessage message =  messageBuilder.build();
       // System.out.println(message);
        wrapper.sendToServer(message);

        wrapper.setCurrent(new StateWait());
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
    public void sendState() {

    }
    @Override
    public void receiveConfirm(ChessMessage.NeoCheMessage msg) {

    }

    @Override
    public void recieveColor( char color) {

    }

    @Override
    public void finishGame() {

        wrapper.setCurrent(new StateEnd());
    }
}
