import java.io.*;
import java.net.Socket;

import CheMessage.ChessMessage;
import Game.*;
import CheMessage.*;
import ru.neochess.phase0.client.CheMessage.*;

/**
 * Created by for on 29.10.16.
 */
public class ClientHandler extends Thread
{
    private Socket clientSock;
    private String cliAddr;
    private QuizServer server;
    public ChessMessage.NeoCheMessage messageIn;
    public ChessMessage.NeoCheMessage messageOut;

    ChessGame game;
    Player player;



    public ClientHandler(Socket s, String cliAddr, QuizServer srv)
    {
        clientSock = s;
        this.cliAddr = cliAddr;
        System.out.println("Client connection from " + cliAddr);
        server = srv;
    }


    public void run()

    {
        boolean done = false;
        try
        {
           DataInputStream is = new DataInputStream(clientSock.getInputStream());

           // byte[] packetData = new byte[is.readInt()];
            //System.out.println("readInt " + is.readInt());
            //is.readFully(packetData);
          /*  ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int read = 0;
            while ((read = is.read(buffer, 0, buffer.length)) != -1) {
                System.out.println(read);
                baos.write(buffer, 0, read);
            }

            messageIn = ChessMessage.NeoCheMessage.parseFrom(buffer);
            System.out.println(messageIn.toString());*/

           // if(is.available() > 0) while((is.read()) > -1) {
              //  messageIn = ChessMessage.NeoCheMessage.parseFrom(is);
            messageIn = ChessMessage.NeoCheMessage.parseDelimitedFrom(is);
                System.out.println(messageIn.toString());
                messageOut = processMessage(messageIn);
           // }

              //OutputStream os = clientSock.getOutputStream();

            System.out.println("out1");


            //byte mes [] = messageOut.toByteArray();

          //  os.write(mes);

          // BufferedReader in  = new BufferedReader(new InputStreamReader( clientSock.getInputStream()));
           // PrintWriter out =  new PrintWriter( clientSock.getOutputStream(), true );

            //processClient(in, out);

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
       // ChessMessage message;

        try
        {
            System.out.println("Client msg: ");
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

    private ChessMessage.NeoCheMessage processMessage(ChessMessage.NeoCheMessage messageIn)
    { if (game == null)
    {game = new ChessGame();}


        String clientState = messageIn.getState();
        switch (clientState) {
            case "ready":
                player = game.setPlayer(messageIn.getUser(0).getName(), 1);

                ChessMessage.User.Builder user = ChessMessage.User.newBuilder();
                user.setName(player.name);
                user.setId(player.ID);
                user.setRace(String.valueOf(player.race));

                ChessMessage.NeoCheMessage.Builder messageBuilder = ChessMessage.NeoCheMessage.newBuilder();
                messageBuilder.addUser(user);
                messageBuilder.setState("steady");
                messageBuilder.setSessionId(game.gameID);
                ChessMessage.NeoCheMessage messageOut =  messageBuilder.build();
                System.out.println(messageOut);
                server.sendMSG(clientSock, messageOut);

                break;
        }

        return messageOut;
    }



}