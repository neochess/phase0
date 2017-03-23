import java.io.*;
import java.net.Socket;


import Game.*;
import CheMessage.*;
import CheMessage.ChessMessage.*;



/**
 * Created by for on 29.10.16.
 */
public class ClientHandler extends Thread
{
    private Socket clientSock;
    private String cliAddr;
    private QuizServer server;
    public NeoCheMessage messageIn;
    public NeoCheMessage messageOut;

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
        Integer n;
        try
        {
           DataInputStream is = new DataInputStream(clientSock.getInputStream());

            while (!done) {

                if (clientSock.isConnected()) {
                    messageIn = NeoCheMessage.parseDelimitedFrom(is);
                    System.out.println(messageIn.toString());
                    processMessage(messageIn);
                }else
                    done = true;
                  //  System.out.println(done);
            }


          // System.out.println("out");

            clientSock.close();
            System.out.println("Client (" + cliAddr + ") connection closed 1 1\n");
            server.removeClient(clientSock);
        }

        catch(Exception e)
        {
           if ( messageIn.getState().equals("end")) {
               game = server.games.stream().filter(game1 -> messageIn.getSessionId().equals(game1.gameID)).findAny().orElse(null);

               System.out.println("game end for gameID: " + game.gameID);
               if (game != null) {
                   server.sendMSG(game.getEnemy(messageIn.getUser(0).getId()).sock, messageIn);// мы просто пересылаем прешедшее сообщение сопернику (возможно стоет пересобрать и поменть статус на moved)
               }
           }

            System.out.println("some disconnect :");
            System.out.println(e);
            System.out.println("Client (" + cliAddr + ") connection closed \n");
            server.removeClient(clientSock);
            server.games.remove(game);

        }
    }



  /*  private void processClient(BufferedReader in, PrintWriter out)
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

    }*/

    private void processMessage(NeoCheMessage messageIn)
    {
        String clientState = messageIn.getState();
        switch (clientState) {
            case "ready":
                if (server.games.isEmpty() == false)
                {
                    game = server.games.get(server.games.size() - 1);
                    if (game.isFull())
                        game = new ChessGame(); server.games.add(game);
                }
                else
                {
                    game = new ChessGame(); server.games.add(game);
                }

                player = game.setPlayer(messageIn.getUser(0).getName(), clientSock);

                if (game.isFull()) {
                    NeoCheMessage.Builder messageBuilder = NeoCheMessage.newBuilder();

                    ChessMessage.User.Builder user = ChessMessage.User.newBuilder();
                   // User.Builder user = User.newBuilder();
                    user.setName(game.getPlayer1().name);
                    user.setId(game.getPlayer1().ID);
                    user.setRace(String.valueOf(game.getPlayer1().race));
                    messageBuilder.addUser(user);

                    //user = User.newBuilder();
                    user = ChessMessage.User.newBuilder();
                    user.setName(game.getPlayer2().name);
                    user.setId(game.getPlayer2().ID);
                    user.setRace(String.valueOf(game.getPlayer2().race));
                    messageBuilder.addUser(user);

                    messageBuilder.setState("steady");
                    messageBuilder.setSessionId(game.gameID);
                    NeoCheMessage messageOut = messageBuilder.build();
                   // System.out.println(messageOut);
                    server.sendMSG(game.getPlayer1().sock, messageOut);
                    server.sendMSG(game.getPlayer2().sock, messageOut);
                }
                break;
            case "move":
                game = server.games.stream().filter(game1 -> messageIn.getSessionId().equals(game1.gameID)).findAny().orElse(null);
                System.out.println("move getted for gameID: " + game.gameID);
                if (game != null)
                {
                    server.sendMSG(game.getEnemy(messageIn.getUser(0).getId()).sock, messageIn);// мы просто пересылаем прешедшее сообщение сопернику (возможно стоет пересобрать и поменть статус на moved)
                }

                break;
            case "end":
                game = server.games.stream().filter(game1 -> messageIn.getSessionId().equals(game1.gameID)).findAny().orElse(null);
                System.out.println("game end for gameID: " + game.gameID);
                if (game != null)
                {
                    server.sendMSG(game.getEnemy(messageIn.getUser(0).getId()).sock, messageIn);// мы просто пересылаем прешедшее сообщение сопернику (возможно стоет пересобрать и поменть статус на moved)
                }

                break;



        }

       // return messageOut;
    }



}