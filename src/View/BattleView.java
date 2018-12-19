package View;

import Controller.Controller;
import Model.Player;
import Model.Gotchi.Gotchi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URL;

public class BattleView{
    Player player1;
    Player computer;

    ObservableList<gotchiProperty> data;

    InputHandler inputHandler = new InputHandler();

    gotchiProperty health;
    gotchiProperty stamina;
    gotchiProperty speed;
    gotchiProperty attack;
    gotchiProperty defense;

    @FXML
    private Label player;

    @FXML
    private TableView playerTable;

    @FXML
    private TableView computerTable;

    @FXML
    private Button dodgeAction;

    @FXML
    private Button restAction;

    @FXML
    private Button dependsAction;

    @FXML
    private Button attackAction;

    @FXML
    private Label playerCommand;

    @FXML
    private Label computerCommand;

    Parent root = loadSceneFromFxml(this);
    Scene battleScene;

    public void attachPlayer(Player player, int playerNumber){
        if(playerNumber == 1)
        {
            this.player1 = player;
            addPlayer(player1, true);
        }else {
            this.computer = player;
            addPlayer(computer, false);
        }
    }

    public BattleView() throws IOException {
        battleScene = new Scene(root, 800, 600);
        System.out.println("Scene created");
    }

    public void dodgeAction(ActionEvent actionEvent) {
        player1.getGotchi().dodge();
        inputHandler.handlePlayersAction();
        playerCommand.setText("DODGE");
    }

    public void restAction(ActionEvent actionEvent) {
        player1.getGotchi().rest();
        inputHandler.handlePlayersAction();
        playerCommand.setText("REST");
    }

    public void dependsAction(ActionEvent actionEvent) {
        playerCommand.setText("DEPENDS");
        player1.getGotchi().defense();
        inputHandler.handlePlayersAction();
    }

    public void attackAction(ActionEvent actionEvent) {
        playerCommand.setText("ATTAK");
        player1.getGotchi().attack();
        inputHandler.handlePlayersAction();
    }

    public void addObserver(Controller controller) {
        inputHandler.addObserver(controller);
    }

    private static Parent loadSceneFromFxml(Object viewController) throws IOException {
        URL pathToFXML = viewController.getClass().getResource("battleView.fxml");
        FXMLLoader loader = new FXMLLoader(pathToFXML);

        loader.setController(viewController);

        return loader.load();
    }

    public Scene getBattleScene() {
        return battleScene;
    }

    private void addPlayer(Player player, boolean isPlayerHuman) {

        TableView currentPlayerTableName;
        Image image = new Image(player.getGotchi().getImageUrl());

        if(isPlayerHuman) {

            currentPlayerTableName = playerTable;
        } else {

            currentPlayerTableName = computerTable;
        }


        this.player.setText(player.getName());
        System.out.println(player.getName());
        Gotchi playersGotchi = player.getGotchi();
        health = new gotchiProperty("Health",playersGotchi.getHealth());
        stamina = new gotchiProperty("Stamina", playersGotchi.getStamina());
        speed = new gotchiProperty("Speed", playersGotchi.getSpeed());
        attack = new gotchiProperty("Attack",playersGotchi.getAttack());
        defense = new gotchiProperty("Defense", playersGotchi.getDefense());
        data = FXCollections.observableArrayList(
                health,
                stamina,
                speed,
                attack,
                defense
            );

        TableColumn firstCol = (TableColumn) currentPlayerTableName.getColumns().get(0);
        firstCol.setCellValueFactory(new PropertyValueFactory<gotchiProperty, String>("Parametr"));
        TableColumn secondCol = (TableColumn) currentPlayerTableName.getColumns().get(1);
        secondCol.setCellValueFactory(new PropertyValueFactory<gotchiProperty, String>("Results"));
        currentPlayerTableName.setItems(data);


    }

    public void updateBattleStats(TableView currentPlayerTableName){

    }
}
