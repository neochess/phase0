import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by for on 29.10.16.
 */
public class ClientHandler extends Thread
{
    private Socket clientSock;
    private String cliAddr;
    private QuizServer server;


    public ClientHandler(Socket s, String cliAddr, QuizServer srv)
    {
        clientSock = s;
        this.cliAddr = cliAddr;
        System.out.println("Client connection from " + cliAddr);
        server = srv;
    }


    public void run()
    {
        try
        {
            BufferedReader in  = new BufferedReader(new InputStreamReader( clientSock.getInputStream()));
            PrintWriter out =  new PrintWriter( clientSock.getOutputStream(), true );

            processClient(in, out);

            clientSock.close();
            System.out.println("Client (" + cliAddr + ") connection closed\n");
            server.removeClient(clientSock);
        }

        catch(Exception e)
        {
            System.out.println(e);
        }
    }



    private void processClient(BufferedReader in, PrintWriter out)
    {
        String line;
        boolean done = false;

        try
        {
            while (!done)
            {
                if((line = in.readLine()) == null) done = true;
                else
                {
                    System.out.println("Client msg: " + line);
                    server.broadcastLine(clientSock, line);
                }
            }
        }

        catch(IOException e)
        {
            System.out.println(e);
        }

    }



}