package ru.neochess.phase0.client.State;

/**
 * Created by TiJi on 04.01.17.
 */
public class ClientStateWrapper {
    private ClientState currentState;

    public ClientStateWrapper() {currentState = new StateReady();

    }
    public void setCurrent( ClientState s ) { currentState = s; }
}