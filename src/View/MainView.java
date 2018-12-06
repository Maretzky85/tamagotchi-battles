package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainView extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        Scene scene2 = new Scene(root, 400, 400);
        primaryStage.setTitle("Gotchi Battle");
        primaryStage.setScene(scene2);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
