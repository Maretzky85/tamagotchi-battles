package View;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
public class LoseScreen {

    public GridPane lose(){
        GridPane gridPane = new GridPane();
        Button button = new Button("Restart Game");


        button.setOnAction(e -> System.out.println("Reset"));


        Image image = new Image("/resources/loser.jpg");
        ImageView imageView = new ImageView(image);
        gridPane.add(button, 1, 100);
        gridPane.add(imageView, 1, 1);
        gridPane.setAlignment(Pos.CENTER);

        return gridPane;
    }
}