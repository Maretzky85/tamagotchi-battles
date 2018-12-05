package View;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FinallStage {
    public GridPane finishGame(){
        GridPane gridPane = new GridPane();
        Button button = new Button("Restart Game");


        button.setOnAction(e -> System.out.println("but"));


        Image image = new Image("/resources/binLaden.jpg");
        ImageView imageView = new ImageView(image);
        gridPane.add(button, 1, 100);
        gridPane.add(imageView, 1, 1);
        gridPane.setAlignment(Pos.CENTER);

        return gridPane;
    }
}
