package Model;

import Model.Gotchi.Gotchi;

public class Player implements Runnable{
    private String name;
    private static int instance = 0;
    private int id = 0;
    private Gotchi gotchi;
    private Battle arena;

    public Player(String name, Battle arena) {
        this.arena = arena;
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

    @Override
    public void run() {
        if (true){
            synchronized (arena){

            }
        }
    }
}
