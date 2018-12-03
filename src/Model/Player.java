package Model;

import Model.Gotchi.Gotchi;

public class Player {
    private String name;
    private int id;
    private Gotchi gotchi;

    public Player(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public void setGotchi(Gotchi gotchi) {
        this.gotchi = gotchi;
    }
}
