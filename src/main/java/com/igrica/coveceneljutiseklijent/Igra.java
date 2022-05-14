package com.igrica.coveceneljutiseklijent;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Igra extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Igra.class.getResource("scene/pocetna.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Covece ne ljuti se");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        //POKRETANJE NITI ZA KOMUNIKACIJU SA SERVEROM
        ServerskaNit serverskaNit = new ServerskaNit();
        serverskaNit.start();
        //POKRETANJE GUI-A
        launch();
    }
}