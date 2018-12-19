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

    private Stage primaryStage;
    Battle arena = new Battle();

    LoginScreen loginView = new LoginScreen();
    ChooseGotchiScene chooseView;
    Scene battleView;

    Player player1 = new Player();
    Player computer = new Player();

    private static Controller instance;

    private Controller(){
    }

    public static Controller getInstance(){
        if(instance == null){
            instance = new Controller();
        }
        return instance;
    }

    public void initiateLoginScreen(Stage stage){
        primaryStage = stage;
        setScene1();
    }

    private void setScene1(){
        loginView.addObserver(this);
        loginView.addObserver(player1);

        Thread arenaRunner = new Thread(arena);
        arenaRunner.setDaemon(true);
        arenaRunner.start();

        primaryStage.setTitle("Gotchi Battles Login");
        primaryStage.setScene(new Scene(loginView));
        primaryStage.show();
    }

    private void setScene2(){
        Thread playerThread = new Thread(computer);
        playerThread.setDaemon(true);
        playerThread.start();

        chooseView = new ChooseGotchiScene(player1);
        chooseView.addObserver(this);
        primaryStage.setScene(new Scene(chooseView, Setup.DISPLAY_WIDTH, Setup.DISPLAY_HEIGHT));
    }

    private void setScene3(){
        arena.addPlayer(player1.getGotchi());
        arena.addPlayer(computer.getGotchi());
        try {
            BattleView battleViewController= new BattleView();
            battleViewController.addObserver(this);
            battleViewController.attachPlayer(player1, 1);
            battleViewController.attachPlayer(computer, 2);
            battleView = battleViewController.getBattleScene();
        } catch (IOException e) {
            System.out.println("File problem");
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

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}

