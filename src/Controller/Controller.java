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
    Battle arena;

    LoginScreen loginView = new LoginScreen();
    ChooseGotchiScene chooseView;
    Scene battleView;

    public Boolean player1moved;
    public Boolean player2moved;


    public Controller(Stage primaryStage){
        loginView.addObserver(this);
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Tamagotchi Battles Login");
        primaryStage.setScene(new Scene(loginView));
        primaryStage.show();
    }

    private void setScene2(String name){
        Battle battle = new Battle(this, this::requestUpdate);
        Thread runner = new Thread(battle);
        this.arena = battle;
        arena.addPlayer(playerMaker(name));
        arena.addPlayer(playerMaker("CPU"));
        chooseView = new ChooseGotchiScene(arena.getPlayer1());
        chooseView.addObserver(this);
        primaryStage.setScene(new Scene(chooseView, Setup.DISPLAY_WIDTH, Setup.DISPLAY_HEIGHT));
    }

    private Player playerMaker(String name){
        Player player = new Player(name, arena);
        Thread runner = new Thread(player);
        runner.setDaemon(true);
        return player;
    }

    private void setScene3(){
        try {
            BattleView battleViewController= new BattleView();
            battleViewController.addObserver(this);
            battleView = battleViewController.getBattleScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setScene(battleView);
    }

    public void requestUpdate(){

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
        if ( arguments[0].equals("playerAction") ){
            System.out.println(arguments[1]);
        }
    }
}

