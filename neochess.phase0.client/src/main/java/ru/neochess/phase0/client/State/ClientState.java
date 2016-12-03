package ru.neochess.phase0.client.State;

/**
 * Created by TiJi on 03.12.16.
 */
public interface ClientState {
    public void sendMove();
    public void receiveMove();
    public void receiveBoard();
    public void sendBoard();


}
