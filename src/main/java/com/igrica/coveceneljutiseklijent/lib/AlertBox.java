package com.igrica.coveceneljutiseklijent.lib;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

    public static void display(String naslov, String poruka){
        Stage stage = new Stage();
        stage.setTitle(naslov);
        Label label = new Label(poruka);
        stage.initModality(Modality.APPLICATION_MODAL);
        Button button = new Button("Zatvori");
        button.setOnAction(e -> stage.close());
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, button);
        layout.setAlignment(Pos.CENTER);
        stage.setMinWidth(250);
        stage.setMinHeight(150);
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();

    }

}
