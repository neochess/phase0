package ru.neochess.phase0.client.State;

/**
 * Created by TiJi on 05.01.17.
 */
public class State {

    public ClientStateWrapper wrapper;
    public String UserName = new String();
    public String Enemy = new String();
    public void setWrapper (ClientStateWrapper wr)
    {
        wrapper = wr;
    }
}
