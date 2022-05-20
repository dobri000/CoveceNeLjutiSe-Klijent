package com.igrica.coveceneljutiseklijent;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Igra extends Application {

    public static Stage stage;
    static Scene scenePocetna, sceneSoba;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        FXMLLoader fxmlLoader1 = new FXMLLoader(Igra.class.getResource("scene/pocetna.fxml"));
        Scene scenePocetna = new Scene(fxmlLoader1.load(), 320, 240);
        stage.setTitle("Covece ne ljuti se");
        stage.setScene(scenePocetna);
        stage.setResizable(false);
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