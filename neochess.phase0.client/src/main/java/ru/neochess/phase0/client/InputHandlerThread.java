package ru.neochess.phase0.client;import java.io.BufferedReader;

/**
 * Created by for on 29.10.16.
 */
public class InputHandlerThread extends Thread
{

    private BufferedReader in;
    ChessServerConnection srvcon;
    boolean done;

    public InputHandlerThread(ChessServerConnection con, BufferedReader i)
    {
        srvcon = con;
        done = false;
        in = i;
    }


    public void run()
    {
        String line;
        try
        {
            while (!done)
            {
                if((line = in.readLine()) == null) done = true;
                else srvcon.reply(line);
                if (done == false) System.out.println("Received board data from server" + line);
            }
        }

        catch(Exception e)
        {
            System.out.println(e);
        }
    }

}

