package Controller;

import Model.Battle;
import Model.Setup;
import View.BattleView;
import View.ChooseGotchiScene;
import View.LoginScreen;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller implements Observer {

    Stage primaryStage;
    Battle arena = new Battle();

    LoginScreen loginView = new LoginScreen();
    ChooseGotchiScene chooseView;
    Scene battleView;




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
        chooseView.addObserver(this);
        primaryStage.setScene(new Scene(chooseView, Setup.DISPLAY_WIDTH, Setup.DISPLAY_HEIGHT));
    }

    private void setScene3(){
        try {
            battleView = new BattleView().getBattleScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setScene(battleView);
    }



    @Override
    public void update(Observable o, Object arg) {
        String[] arguments = (String[]) arg;
        if ( arguments[0].equals("loginScreen") ){
            setScene2(arguments[0]);
        }
        if ( arguments[0].equals("gotchiChoose") ){
            setScene3();
        }
    }
}

