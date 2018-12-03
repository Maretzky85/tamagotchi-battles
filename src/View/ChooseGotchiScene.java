package View;

import Model.ChooseGotchi;
import Model.Gotchi.Gotchi;
import Model.Player;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class ChooseGotchiScene extends Pane {

    public ChooseGotchiScene() {
        int x = 0;
        int y = 0;
        int margin = 90;
        ChooseGotchi newGotchi = new ChooseGotchi(new Player("Marchewka", 1));
        for (Gotchi singleGotchi : newGotchi.gotchies) {
            singleGotchi.setImage(new Image(singleGotchi.getImageUrl()));
            Text text = new Text();
            text.setText("Name: " + singleGotchi.getName() + "\n" +
                         "Attack: " + singleGotchi.getAttack() + "\n" +
                         "Defense: " + singleGotchi.getDefense() + "\n" +
                         "Speed: " + singleGotchi.getSpeed());
            singleGotchi.setX(x);
            singleGotchi.setY(y);
            text.setX(x + margin);
            text.setY(y);
            this.getChildren().add(text);
            this.getChildren().add(singleGotchi);
            x += 100;
            y += 100;
        }
    }
}
