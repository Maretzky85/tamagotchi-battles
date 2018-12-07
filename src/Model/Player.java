package Model;

import Model.Gotchi.Gotchi;

public class Player {
    private String name;

    private static int instance = 0;
    private int id = 0;
    private Gotchi gotchi;
    public Player(String name) {
        this.name = name;
        this.id = instance;
        instance++;
    }

    public void setGotchi(Gotchi gotchi) {
        this.gotchi = gotchi;
    }

    public Gotchi getGotchi() {
        return gotchi;
    }

    public String getName() {
        return name;
    }
}
