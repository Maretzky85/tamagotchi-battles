package Model;

import Model.Gotchi.Gotchi;

import java.util.Observable;
import java.util.Observer;

public class Player implements Runnable, Observer{

    private String name;

    private static int instance = 0;
    private int id = 0;
    private Gotchi gotchi;
    public Player() {
        this.id = instance;
        instance++;
    }

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

    public void setName(String name) {
        System.out.println("Player: Name set to "+name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        while (true){
            if(name == null){
                setName("CPU");
                chooseRandomGotchi();
                waitForChange();
            }
            if(gotchi.getAction() == null){
                int action = (int) (Math.random() * 3);
                switch (action){
                    case 1:
                        gotchi.attack();
                        break;
                    case 2:
                        gotchi.defense();
                        break;
                    case 3:
                        gotchi.dodge();
                        break;
                    default:
                        gotchi.attack();
                        break;
                }

                waitForChange();
            }
        }
    }

    private void waitForChange() {
        synchronized (gotchi){
            try {
                gotchi.wait();
            } catch (InterruptedException ignored) {}
        }
    }

    private void chooseRandomGotchi() {
        ChooseGotchi chooseGotchi = new ChooseGotchi(this);
        chooseGotchi.setPickedGotchie(chooseGotchi.gotchies.get((int)(Math.random() * 6)));
        chooseGotchi.setGotchi();
    }

    @Override
    public void update(Observable o, Object arg) {
        String[] arguments = (String[]) arg;
        this.name = arguments[1];
        System.out.println("Player: Name set to: "+name);
    }

}
