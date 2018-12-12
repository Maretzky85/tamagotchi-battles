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

    boolean isLoginValid(String login) {
        if(login.equals("")) {
            return false;
        }

        char[] charArray = login.toCharArray();
        for(char loginCharacter : charArray) {
            int asc = (int) loginCharacter;
            if( asc != 45 && asc < 48 || asc < 65 && asc > 57 || asc > 122 || asc > 90 && asc < 97 && asc !=95){
                return false;
            }
        }
        return true;
    }

    public void handleChooseGothi() {
        infoToSend = new String[2];
        infoToSend[0] = "gotchiChoose";
        infoToSend[1] = "choosed";
        setChanged();
        notifyObservers(infoToSend);
    }

    public void handlePlayersAction(){
        infoToSend = new String[1];
        infoToSend[0] = "PlayersAction";
        setChanged();
        notifyObservers(infoToSend);
    }
}
