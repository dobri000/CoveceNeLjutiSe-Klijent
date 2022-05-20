package com.igrica.coveceneljutiseklijent;

import com.igrica.coveceneljutiseklijent.kontrolori.PocetnaKontroler;
import com.igrica.coveceneljutiseklijent.lib.PodaciOIgracima;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SobaInfoNit extends Thread{

    ObjectInputStream in;
    ObjectOutputStream out;

    public SobaInfoNit(ObjectOutputStream out, ObjectInputStream in){
        this.in = in;
        this.out = out;
    }

    @Override
    public void run() {
        while(true){
            try {
                int brojIgraca = (int) in.readObject(); //Primanje o broju igraca u sobi
                //Primanje informacija za svakog igraca posebno
                for(int i = 0; i < brojIgraca; i++) {
                    String ime = (String) in.readObject();
                    String boja = (String) in.readObject();
                    ServerskaNit.trenutniPodaciOIgracima[i] = new PodaciOIgracima(ime, boja);
                }
                switch (brojIgraca){
                    case 4:
                        PocetnaKontroler.sobaKontroler.textFieldIme4.setText(ServerskaNit.trenutniPodaciOIgracima[3].ime);
                    case 3:
                        PocetnaKontroler.sobaKontroler.textFieldIme3.setText(ServerskaNit.trenutniPodaciOIgracima[2].ime);
                    case 2:
                        PocetnaKontroler.sobaKontroler.textFieldIme2.setText(ServerskaNit.trenutniPodaciOIgracima[1].ime);
                    case 1:
                        PocetnaKontroler.sobaKontroler.textFieldIme1.setText(ServerskaNit.trenutniPodaciOIgracima[0].ime);
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
