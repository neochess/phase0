package ru.neochess.phase0.client;
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

    public ChessServerConnection(ChessBoard cb)
    {
        chessboard = cb;

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


    public synchronized void reply(String line)
    {
        chessboard.receiveData(line);
    }



}
