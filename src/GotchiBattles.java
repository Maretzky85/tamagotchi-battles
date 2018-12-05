import Controller.Controller;
import Model.Setup;
import View.ChooseGotchiScene;
import View.LoginScreen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GotchiBattles extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Controller controller = new Controller(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
