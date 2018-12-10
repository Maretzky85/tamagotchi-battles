package View;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

    public static void display(String title, String message){
        Stage alertBox = new Stage();

        alertBox.initModality(Modality.APPLICATION_MODAL);
        alertBox.setTitle(title);
        alertBox.setMinWidth(250);

        Label alertMessageLabel = new Label(message);
        Button closeButton = new Button("Ok");
        closeButton.setOnAction(e -> alertBox.close());

        VBox layout = new VBox(10);
        ((VBox) layout).getChildren().addAll(alertMessageLabel, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        alertBox.setScene(scene);
        alertBox.showAndWait();
    }
}
