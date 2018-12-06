package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class BattleView{
    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));;
    Scene battleScene;

    public BattleView() throws IOException {
        battleScene = new Scene(root, 400, 400);
    }

    public Scene getBattleScene() {
        return battleScene;
    }

}
