package Controller;

import Model.Battle;
import Model.ChooseGotchi;
import View.LoginScreen;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;

public class Controller implements Observer {

    Stage primaryStage;
    Battle arena = new Battle();
    LoginScreen loginView = new LoginScreen();
    ChooseGotchi chooseView;


    public Controller(Stage primaryStage){
        loginView.addObserver(this);
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Tamagotchi Battles Login");
        primaryStage.setScene(new Scene(loginView));
        primaryStage.show();
    }

    @Override
    public void update(Observable o, Object arg) {
        String[] arguments = (String[]) arg;
        if ( arguments[0].equals("loginScreen") ){
            arena.addPlayer(arguments[1]);
        }
        chooseView = new ChooseGotchi(arena.getPlayer1());
    }
}
