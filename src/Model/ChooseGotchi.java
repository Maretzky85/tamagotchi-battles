package Model;

import Model.Gotchi.Gotchi;
import Model.Gotchi.Juliusz;

import java.util.Arrays;
import java.util.List;

public class ChooseGotchi {

    // public, so the view can iterate through all gotchies in order to display them
    public List<Gotchi> gotchies = Arrays.asList(new Juliusz(), new Juliusz(), new Juliusz(), new Juliusz(), new Juliusz(), new Juliusz());

    private Gotchi pickedGotchi;

    private Player player;

    public ChooseGotchi(Player player) {
        this.player = player;

    }

    // call this method whenever user selects this Gotchi without proceeding further
    public void setPickedGotchie(Gotchi pickedGotchi) {
        this.pickedGotchi = pickedGotchi;
    }

    // cal this method when user decide to go further
    public void setGotchi() {
        if (pickedGotchi != null) {
            player.setGotchi(this.pickedGotchi);
        } else {
            System.out.println("No Gotchi selected. Please, try again.");
        }
    }

}
