package View;

import Controller.Controller;
import Model.ChooseGotchi;
import Model.Gotchi.Gotchi;
import Model.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class ChooseGotchiScene extends Pane {
    //TODO get player from previous scene
    InputHandler inputHandler = new InputHandler();
    private Player player;
    private ChooseGotchi newGotchi = new ChooseGotchi(player);
    private final ToggleGroup gotchiesGroup = new ToggleGroup();

    public ChooseGotchiScene(Player player) {
        this.player = player;
        displayGotchies();
        proceedButton();
    }

    public void addObserver(Controller controller) {
        inputHandler.addObserver(controller);
    }

    private void displayGotchies() {
        int x = 320;
        int y = 100;
        int counter = 0;
        for (Gotchi singleGotchi : newGotchi.gotchies) {
            if (counter == 3) {
                x = 320;
                y = 300;
            }
            Image image = new Image(singleGotchi.getImageUrl());
            singleGotchi.setImage(image);
            singleGotchi.setX(x);
            singleGotchi.setY(y);

            Text text = new Text();
            text.setText(
                    "Attack: " + singleGotchi.getAttack() + "\n" +
                    "Defense: " + singleGotchi.getDefense() + "\n" +
                    "Speed: " + singleGotchi.getSpeed() + "\n" +
                    "Type: " + singleGotchi.getPrimary() + " / " + singleGotchi.getSecondary());
            text.setX(x + image.getWidth());
            text.setY(y + 30);
            displayRadioButtons(x + (int) image.getWidth(), y, singleGotchi.getName());
            this.getChildren().add(text);
            this.getChildren().add(singleGotchi);
            x += 200;
            counter++;
        }
    }

    private void displayRadioButtons(int x, int y, String name) {
        RadioButton rb1 = new RadioButton(name);
        rb1.setToggleGroup(gotchiesGroup);
        rb1.setLayoutY(y);
        rb1.setLayoutX(x);
        getChildren().add(rb1);
    }

    private void proceedButton() {
        Button button = new Button("Proceed");
        button.setLayoutX(600);
        button.setLayoutY(450);
        getChildren().add(button);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                if (gotchiesGroup.getSelectedToggle() == null) {
                    alert("You must choose Gotchi in order to proceed further.");
                } else {
                    player.setGotchi(newGotchi.gotchies.get(gotchiesGroup.getToggles().indexOf(gotchiesGroup.getSelectedToggle())));
                    System.out.println(player.getName()+": picked gotchi: "+
                            newGotchi.gotchies.get(gotchiesGroup.getToggles().indexOf(gotchiesGroup.getSelectedToggle())).getName()
                    );
                    inputHandler.handleChooseGothi();
                    //TODO: load next Scene
                }
            }
        });
    }

    private void alert(String alertMessage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION.INFORMATION);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(alertMessage);
        alert.showAndWait();
    }
}
