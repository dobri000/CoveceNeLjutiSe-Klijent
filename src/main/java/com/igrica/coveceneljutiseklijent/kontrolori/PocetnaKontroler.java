package com.igrica.coveceneljutiseklijent.kontrolori;

import com.igrica.coveceneljutiseklijent.ServerskaNit;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;


public class PocetnaKontroler {

    @FXML
    TextField sifraSobe;

    @FXML
    public void kreirajSobu(){
        ServerskaNit.kreirajSobu();
    }

    @FXML
    public void pridruziSeSobi(){
        String text = sifraSobe.getText();
        if(validnostSifre(text) == false){
            sifraSobe.setText(null);
            //TODO: ALERT BOX ZA OBAVESTENJE O LOSEM UNOSENJU SIFRE
        } else{
            ServerskaNit.pridruziSeSobi(text);
        }
    }

    private boolean validnostSifre(String text){
        try{
            int sifra = Integer.parseInt(text);
            if(sifra < 1000 || sifra > 9999)
                return false;
            return true;
        } catch (Exception e){
            return false;
        }
    }
}