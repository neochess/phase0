package ru.neochess.phase0.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import ru.neochess.phase0.client.CheMessage.ChessMessage.*;
/**
 * Created by for on 29.10.16.
 */
public class InputHandlerThread extends Thread
{

    private BufferedReader in;
    ChessServerConnection srvcon;
    NeoCheMessage messageIn;
    boolean done;
    InputStream is;

    public InputHandlerThread(ChessServerConnection con, BufferedReader i, InputStream inputs)
    {
        srvcon = con;
        done = false;
        in = i;
        is = inputs;
    }


        public void run()
        {
            try {
                while (!done) {

                    messageIn = NeoCheMessage.parseDelimitedFrom(is);
                    System.out.println(messageIn.toString());
                   // System.out.println(messageIn);

                    if (messageIn != null)
                        srvcon.reply(messageIn );
                }
            } catch (IOException e) {
                System.out.println("some disconnect");
                e.printStackTrace();
            }



       /* String line;
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
        }*/
    }

}

