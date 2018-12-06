package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Observable;

public class InputHandler extends Observable {
    String[] infoToSend = new String[2];

    @FXML
    private Button dodgeAction;
    @FXML
    private Button restAction;
    @FXML
    private Button dependsAction;
    @FXML
    private Button attackAction;
    @FXML
    private Label playerCommand;
    @FXML
    private Label computerCommand;

    public void dodgeAction(ActionEvent actionEvent) {
        playerCommand.setText("DODGE");
    }

    public void restAction(ActionEvent actionEvent) {
        playerCommand.setText("REST");
    }

    public void dependsAction(ActionEvent actionEvent) {
        playerCommand.setText("DEPENDS");
    }

    public void attackAction(ActionEvent actionEvent) {
        playerCommand.setText("ATTAK");
    }

    public void handleLogin(String login) {
        infoToSend = new String[2];
        infoToSend[0] = "loginScreen";
        infoToSend[1] = login;
        setChanged();
        notifyObservers(infoToSend);
    }


    public void handleChooseGothi() {
        infoToSend = new String[2];
        infoToSend[0] = "gotchiChoose";
        infoToSend[1] = "choosed";
        setChanged();
        notifyObservers(infoToSend);
    }
}
