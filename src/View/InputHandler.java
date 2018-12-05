package View;

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
}
