package ru.neochess.phase0.client;
import ru.neochess.phase0.client.CheMessage.ChessMessage;
import ru.neochess.phase0.client.State.ClientStateWrapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


/**
 * Created by for on 29.10.16.
 */
public class ChessServerConnection
{
    private static final int PORT = Integer.parseInt(UtiliteChess.getInstance().getPort());
    private static final String HOST = UtiliteChess.getInstance().getHost();
    private InputHandlerThread inputhandler;


    ChessBoard chessboard;


    private Socket sock;
    private BufferedReader in;
    private PrintWriter out;
   private ClientStateWrapper clientState;

    public ChessServerConnection(ChessBoard cb , ClientStateWrapper cs)
    {
        chessboard = cb;
        clientState = cs;


        System.out.println(HOST);

        try
        {

            sock = new Socket(HOST, PORT);
            in  = new BufferedReader(new InputStreamReader( sock.getInputStream()));
            out = new PrintWriter( sock.getOutputStream(), true );
            inputhandler = new InputHandlerThread(this, in);
            inputhandler.start();

        }

        catch(Exception e)
        {
            System.out.println(e);
        }
    }


    public void send(String line)
    {
        out.println(line);
    }
    public void sendMessage(ChessMessage.NeoCheMessage message)
    {
        out.println(message);
    }


    public synchronized void reply(String line)
    {
        clientState.processMSG(line);
    }



}
