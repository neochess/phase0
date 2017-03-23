
import Game.ChessGame;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;
import CheMessage.ChessMessage.*;

/**
 * Created by for on 29.10.16.
 */
public class QuizServer {

    private static final int PORT = 5000;     // TCP Port
    ArrayList <Socket> client_socks = new ArrayList<Socket> ();
    public ArrayList<ChessGame> games = new ArrayList<ChessGame>();

    public QuizServer(ChessServer chessServer) {

        try {
            ServerSocket serverSock = new ServerSocket(PORT);
            Socket clientSock;
            String cliAddr;

            while (true) {
                System.out.println("Waiting for a client...");
                clientSock = serverSock.accept();

                if (client_socks.size() >= 100) {
                    System.out.println("Server connection capacity reached.");
                    clientSock.close();
                    continue;
                }

                client_socks.add(clientSock);


                cliAddr = clientSock.getInetAddress().getHostAddress();
                new ClientHandler(clientSock, cliAddr, this).start();
            }


        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public synchronized void sendMSG (Socket clientsock, NeoCheMessage messageOut)
    {
        try {
            DataOutputStream out = new DataOutputStream (clientsock.getOutputStream());
            System.out.println("sending " + messageOut);

            messageOut.writeDelimitedTo(out);
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public synchronized void removeClient(Socket clientSock) {
        client_socks.remove(clientSock);
    }


}
