package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Observable;

public class InputHandler extends Observable {
    String[] infoToSend = new String[2];

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
