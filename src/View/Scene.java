package View;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Scene {
    public void sceneChanger(Pane pane, Stage window, javafx.scene.Scene scene) {
        Pane pane2 = new Pane();
        javafx.scene.Scene scene1 = new javafx.scene.Scene(pane2);

        Button but1 = new Button("FUCK YOU");
        pane.getChildren().add(but1);
        but1.setOnAction(e -> window.setScene(scene1));

        Button but2 = new Button("go back");
        but2.setOnAction(e -> window.setScene(scene));
        pane2.getChildren().add(but2);

    }
    public void finishGame(){
        Pane pane = new Pane();
        Button button = new Button("Restart Game");
        button.setOnAction(e -> System.out.println("but"));
        ImageView imageView = new ImageView("/resources/binLaden.png");
        pane.getChildren().addAll(button, imageView);
    }
}
