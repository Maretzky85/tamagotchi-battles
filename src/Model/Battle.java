package Model;

import java.util.ArrayList;

public class Battle {
    ArrayList<Player> players;

    public void addPlayer(String name){
        players.add(new Player(name));
    }

    public Player getPlayer1(){
        return players.get(0);
    }
}
