package Controller;

import Model.Battle;
import Model.Player;
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
    Scene battleView;

    Player player1 = new Player();
    Player player2 = new Player();



    public Controller(Stage primaryStage){
        loginView.addObserver(this);
        loginView.addObserver(player1);
        this.primaryStage = primaryStage;

        Thread arenaRunner = new Thread(arena);
        arenaRunner.setDaemon(true);
        arenaRunner.start();

        primaryStage.setTitle("Gotchi Battles Login");
        primaryStage.setScene(new Scene(loginView));
        primaryStage.show();
    }

    private void setScene2(){
        Thread playerThread = new Thread(player2);
        playerThread.setDaemon(true);
        playerThread.start();

        chooseView = new ChooseGotchiScene(player1);
        chooseView.addObserver(this);
        primaryStage.setScene(new Scene(chooseView, Setup.DISPLAY_WIDTH, Setup.DISPLAY_HEIGHT));
    }

    private void setScene3(){
        arena.addPlayer(player1.getGotchi());
        arena.addPlayer(player2.getGotchi());
        try {
            BattleView battleViewController= new BattleView();
            battleViewController.addObserver(this);
            battleViewController.attachPlayer(player1);
            battleView = battleViewController.getBattleScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setScene(battleView);
        synchronized (arena){
            arena.notify();
        }

    }



    @Override
    public void update(Observable o, Object arg) {
        String[] arguments = (String[]) arg;
        if ( arguments[0].equals("loginScreen") ){
            setScene2();
        }
        if ( arguments[0].equals("gotchiChoose") ){
            setScene3();
        }
        if ( arguments[0].equals("PlayersAction") ){
            synchronized (arena){
                arena.notify();
            }
        }
    }
}

