import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

/**
 * Created by for on 29.10.16.
 */
public class QuizServer
{

    private static final int PORT = 5000;     // TCP Port
    Vector client_socks = new Vector(1);

    public QuizServer(ChessServer chessServer)
    {

        try
        {
            ServerSocket serverSock = new ServerSocket(PORT);
            Socket clientSock;
            String cliAddr;

            while (true)
            {
                System.out.println("Waiting for a client...");
                clientSock = serverSock.accept();

                if (client_socks.size() >= 2)
                {
                    System.out.println("Server connection capacity reached.");
                    clientSock.close();
                    continue;
                }

                client_socks.addElement(clientSock);

                //sendIdentity();

                cliAddr = clientSock.getInetAddress().getHostAddress();
                new ClientHandler(clientSock, cliAddr, this).start();
            }


        }

        catch(Exception e)
        {
            System.out.println(e);
        }
    }




	/*sock = (Socket) client_socks.get(0);
	try
	{
		out = new PrintWriter( sock.getOutputStream(), true );
		out.println("@WHITE");
		out.flush();
	}

	catch(Exception e)
    	{
 		System.out.println(e);
    	}*/

    public synchronized void removeClient(Socket clientSock)
    {
        client_socks.removeElement(clientSock);
    }


}
