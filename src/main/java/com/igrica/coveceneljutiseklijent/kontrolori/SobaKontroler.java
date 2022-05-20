package com.igrica.coveceneljutiseklijent.kontrolori;

import com.igrica.coveceneljutiseklijent.ServerskaNit;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SobaKontroler {

    @FXML
    Label lblBrojSobe;

    @FXML
    public TextField textFieldIme1;

    @FXML
    public TextField textFieldIme2;

    @FXML
    public TextField textFieldIme3;

    @FXML
    public TextField textFieldIme4;

    @FXML
    public Button button1;

    @FXML
    public Button button2;

    @FXML
    public Button button3;

    @FXML
    public Button button4;

    public void postaviBrojSobe(int broj){
        lblBrojSobe.setText("" + broj);
    }

    @FXML
    public void promeniIme1(){
        ServerskaNit.menjanjeImenaIgraca(1, textFieldIme1.getText());
    }

    @FXML
    public void promeniIme2(){
        ServerskaNit.menjanjeImenaIgraca(2, textFieldIme2.getText());
    }

    @FXML
    public void promeniIme3(){
        ServerskaNit.menjanjeImenaIgraca(3, textFieldIme3.getText());
    }

    @FXML
    public void promeniIme4(){
        ServerskaNit.menjanjeImenaIgraca(4, textFieldIme4.getText());
    }

}
