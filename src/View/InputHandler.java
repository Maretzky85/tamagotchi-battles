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


    public void handleChooseGothi() {
        infoToSend = new String[2];
        infoToSend[0] = "gotchiChoose";
        infoToSend[1] = "choosed";
        setChanged();
        notifyObservers(infoToSend);
    }

    public void handleBattle(String action) {
        infoToSend = new String[3];
        infoToSend[0] = "Battle";
        infoToSend[1] = "PlayerAction";
        infoToSend[2] = action;
        setChanged();
        notifyObservers(infoToSend);
    }
}
