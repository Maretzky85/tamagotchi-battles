package View;

import Model.Player;
import Model.Setup;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainView extends Application {
    Player player = new Player("Marchewka", 1);

    @Override
    public void start(Stage primaryStage) throws Exception{
        Scene gotchiScene = new Scene(new ChooseGotchiScene(player), Setup.DISPLAY_WIDTH, Setup.DISPLAY_HEIGHT);
        primaryStage.setTitle("Tamagotchi Battles");
        primaryStage.setScene(gotchiScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
