package View;

import Controller.Controller;
import Model.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;

public class BattleView{
    Player player;

    InputHandler inputHandler = new InputHandler();

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
        this.player = player;
    }

    public BattleView() throws IOException {
        battleScene = new Scene(root, 400, 400);
    }

    public void dodgeAction(ActionEvent actionEvent) {
        player.getGotchi().dodge();
        inputHandler.handlePlayersAction();
        playerCommand.setText("DODGE");
    }

    public void restAction(ActionEvent actionEvent) {
        player.getGotchi().rest();
        inputHandler.handlePlayersAction();
        playerCommand.setText("REST");
    }

    public void dependsAction(ActionEvent actionEvent) {
        playerCommand.setText("DEPENDS");
        player.getGotchi().defense();
        inputHandler.handlePlayersAction();
    }

    public void attackAction(ActionEvent actionEvent) {

        playerCommand.setText("ATTAK");
        player.getGotchi().attack();
        inputHandler.handlePlayersAction();
    }

    public void addObserver(Controller controller) {
        inputHandler.addObserver(controller);
    }

    private static Parent loadSceneFromFxml(Object viewController) throws IOException {
        URL pathToFXML = viewController.getClass().getResource("sample.fxml");
        FXMLLoader loader = new FXMLLoader(pathToFXML);

        loader.setController(viewController);

        return loader.load();
    }

    public Scene getBattleScene() {
        return battleScene;
    }

}
