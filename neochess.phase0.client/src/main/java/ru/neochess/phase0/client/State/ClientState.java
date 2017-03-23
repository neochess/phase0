package ru.neochess.phase0.client.State;
import ru.neochess.phase0.client.CheMessage.ChessMessage.*;

import ru.neochess.phase0.client.ChessBoard;

/**
 * Created by TiJi on 03.12.16.
 */
public interface ClientState {

    public void setWrapper (ClientStateWrapper wr);
    public void sendMove( String board, String move);
    public void receiveMove(ChessBoard chessboard, String line, String move);
    public void receiveState(String state);
    public void receiveConfirm(NeoCheMessage msg);
    public void sendState();
    public void recieveColor( char color);
    public void finishGame();

}
