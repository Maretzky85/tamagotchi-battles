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

import java.io.IOException;
import java.net.URL;

public class BattleView{
    Player player1;

    ObservableList<Property> data;

    InputHandler inputHandler = new InputHandler();

    Property health;
    Property stamina;
    Property speed;
    Property attack;
    Property defense;

    @FXML
    private Label player;

    @FXML
    private TableView playerTable;

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

    public void attachPlayer(Player player){
        this.player1 = player;
        addPlayer(player1);
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

    public void addPlayer(Player player1) {
        player.setText(player1.getName());
        Gotchi playersGotchi = player1.getGotchi();
        health = new Property("Health",playersGotchi.getHealth());
        stamina = new Property("Stamina", playersGotchi.getStamina());
        speed = new Property("Speed", playersGotchi.getSpeed());
        attack = new Property("Attack",playersGotchi.getAttack());
        defense = new Property("Defense", playersGotchi.getDefense());
        data = FXCollections.observableArrayList(
                health,
                stamina,
                speed,
                attack,
                defense
        );
        TableColumn firstCol = (TableColumn) playerTable.getColumns().get(0);
        firstCol.setCellValueFactory(new PropertyValueFactory<Property, String>("Parametr"));
        TableColumn secondCol = (TableColumn) playerTable.getColumns().get(1);
        secondCol.setCellValueFactory(new PropertyValueFactory<Property, String>("Results"));
        playerTable.setItems(data);


    }
}
