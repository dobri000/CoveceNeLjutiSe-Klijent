package com.igrica.coveceneljutiseklijent.kontrolori;

import com.igrica.coveceneljutiseklijent.Igra;
import com.igrica.coveceneljutiseklijent.ServerskaNit;
import com.igrica.coveceneljutiseklijent.lib.AlertBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class PocetnaKontroler {

    @FXML
    TextField sifraSobe;

    public static SobaKontroler sobaKontroler;

    @FXML
    public void kreirajSobu(ActionEvent event) throws IOException {

        //Ucitavanje nove scene
        FXMLLoader fxmlLoader = new FXMLLoader(Igra.class.getResource("scene/soba.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //Dobijanje stage-a
        stage.setScene(scene); //Postavljanje scene
        stage.show();
        sobaKontroler =(SobaKontroler) fxmlLoader.getController(); //Dobijanje kontrolora za scenu sobe
        //Personalizacija scene za prvog igraca
        sobaKontroler.button1.setVisible(true);
        sobaKontroler.textFieldIme1.setEditable(true);
        sobaKontroler.textFieldIme1.setText("Igrac 1");
        ServerskaNit.kreirajSobu(); //Poziv metode iz Serverske niti za dobijanje broja za sobu
    }

    @FXML
    public void pridruziSeSobi() {
        String text = sifraSobe.getText();
        if (validnostSifre(text) == false) { //Proverava se validnost sifre
            AlertBox.display("Los unos", "Neuspesno ste uneli sifru"); //U slucaju da nije izbacuje AlertBox
            sifraSobe.setText(null); //Resetuje polje da u njemu ne pise nista
        } else {
            ServerskaNit.pridruziSeSobi(Integer.parseInt(text)); //U slucaju da jeste sifra validna, pokrece se metoda iz serverske niti
        }
    }

    private boolean validnostSifre(String text) {
        try {
            int sifra = Integer.parseInt(text); //Pokusaj pretvaranja dobijenog teksta u integer
            if (sifra < 0 || sifra > 9999) //Proveravanje da li je u rasponu
                return false;
            return true;
        } catch (NumberFormatException e) {
            return false; //U slucaju da nije uspeo da parsira text
        }
    }

}