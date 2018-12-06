package Controller;

import Model.Battle;
import Model.Setup;
import View.ChooseGotchiScene;
import View.LoginScreen;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;

public class Controller implements Observer {

    Stage primaryStage;
    Battle arena = new Battle();

    LoginScreen loginView = new LoginScreen();
    ChooseGotchiScene chooseView;


    public Controller(Stage primaryStage){
        loginView.addObserver(this);
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Tamagotchi Battles Login");
        primaryStage.setScene(new Scene(loginView));
        primaryStage.show();
    }

    private void setScene2(String name){
        arena.addPlayer(name);
        chooseView = new ChooseGotchiScene(arena.getPlayer1());
        primaryStage.setScene(new Scene(chooseView, Setup.DISPLAY_WIDTH, Setup.DISPLAY_HEIGHT));
    }

    @Override
    public void update(Observable o, Object arg) {
        String[] arguments = (String[]) arg;
        if ( arguments[0].equals("loginScreen") ){
            setScene2(arguments[0]);
        }


    }
}
