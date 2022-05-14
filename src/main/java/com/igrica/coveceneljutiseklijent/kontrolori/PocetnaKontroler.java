package com.igrica.coveceneljutiseklijent.kontrolori;

import com.igrica.coveceneljutiseklijent.ServerskaNit;
import com.igrica.coveceneljutiseklijent.lib.AlertBox;
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
            AlertBox.display("Los unos", "Neuspesno ste uneli sifru");
            sifraSobe.setText(null);
        } else{
            ServerskaNit.pridruziSeSobi(Integer.parseInt(text));
        }
    }

    private boolean validnostSifre(String text){
        try{
            int sifra = Integer.parseInt(text);
            if(sifra < 0 || sifra > 9999)
                return false;
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
}