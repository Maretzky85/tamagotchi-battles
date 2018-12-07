package Controller;

import Model.Battle;
import Model.Setup;
import View.BattleView;
import View.ChooseGotchiScene;
import View.LoginScreen;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class Controller implements Observer {

    Stage primaryStage;
    Battle arena = new Battle();

    LoginScreen loginView = new LoginScreen();
    ChooseGotchiScene chooseView;
    BattleView battleView;
    Scene battleScene;




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
            battleView = new BattleView();
            battleView.addObserver(this);
        } catch (IOException e) {
            System.out.println("File problem");
        }
        battleScene = battleView.getBattleScene();
        primaryStage.setScene(battleScene);
        battleView.addPlayer(arena.getPlayer1());
    }



    @Override
    public void update(Observable o, Object arg) {
        String[] arguments = (String[]) arg;
        if ( arguments[0].equals("loginScreen") ){
            setScene2(arguments[1]);
        }
        if ( arguments[0].equals("gotchiChoose") ){
            setScene3();
        }
        if ( arguments[0].equals("Battle") ){
            if ( arguments[1].equals("PlayerAction") ){
                if ( arguments[2].equals("ATTAK") ){
                    arena.getPlayer1().getGotchi().attack();
                }

            }
        }
    }
}

