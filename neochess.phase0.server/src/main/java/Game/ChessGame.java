package Game;

import java.util.UUID;

/**
 * Created by TiJi on 14.03.17.
 */
public class ChessGame {
    public String gameID = new String();
    private Player player1;
    private Player player2;
    private boolean fullPlayerSet = false;

    public Player setPlayer(String Name, Integer socketID) {
        if (fullPlayerSet == false) {
            if (player1 == null) {
                gameID = UUID.randomUUID().toString();
                player1 = new Player();
                player1.race = 'P';
                player1.ID = gameID + "_1";
                player1.name = Name;
                player1.socketID = socketID;

                System.out.println("Game:" + gameID + " Player1: " + player1.ID);
                return player1;

            } else if (player2 == null) {
                player2 = new Player();
                player2.race = 'A';
                player2.ID = gameID + "_2";
                player2.name = Name;
                player2.socketID = socketID;

                fullPlayerSet = true;
                System.out.println("Game:" + gameID + "Player2: " + player1.toString());

                return player2;
            }
        }


        return null;
    }
}
