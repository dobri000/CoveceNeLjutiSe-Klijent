package com.igrica.coveceneljutiseklijent;

import com.igrica.coveceneljutiseklijent.kontrolori.PocetnaKontroler;
import com.igrica.coveceneljutiseklijent.kontrolori.SobaKontroler;
import com.igrica.coveceneljutiseklijent.lib.AlertBox;
import com.igrica.coveceneljutiseklijent.lib.PodaciOIgracima;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerskaNit extends Thread{
    Socket socket;
    public static ObjectOutputStream out;
    public static ObjectInputStream in;
    public static int brojSobe;
    public static PodaciOIgracima[] trenutniPodaciOIgracima = new PodaciOIgracima[4];

    @Override
    public void run() {
        try{
            socket = new Socket("localhost", 6666);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch(IOException e){
//TODO: dodati izlaz
        }
    }

    public static void kreirajSobu(){
        try {
            //Slanje opcije za kreiranje sobe
            out.writeObject(1);

            //Primanje broja kreirane sobe
            brojSobe = (int) in.readObject();

            trenutniPodaciOIgracima[0] = new PodaciOIgracima("Igrac 1", "plava");

            //Postavljanje broja sobe u novoj sceni za sobu
            PocetnaKontroler.sobaKontroler.postaviBrojSobe(brojSobe);

            //Nit za komunikaciju za azuriranje podataka u sobi
            SobaInfoNit sobaInfoNit = new SobaInfoNit(out, in);
            sobaInfoNit.start();

        } catch (IOException e) {
//TODO: dodati izlaz
        } catch (ClassNotFoundException e) {
            System.out.println("Nije poslat pravi podatak");
        }
    }

    public static void menjanjeImenaIgraca(int rbIgraca, String novoIme){
        trenutniPodaciOIgracima[rbIgraca - 1].ime = novoIme;
        try{
            out.writeObject(3);
            out.writeObject(rbIgraca);
            out.writeObject(novoIme);
        }catch (IOException e){

        }
    }

    public static void pridruziSeSobi(int text){
        try {
            //Slanje opcije za pridruzivanje sobi
            out.writeObject(2);

            //Slanje broja sobe
            out.writeObject(text);

            //Primanje da li je nasao sobu i upisao igraca u nju
            boolean pridruzio = (boolean) in.readObject();
            if(pridruzio){
                PodaciOIgracima[] podaciOIgracima = new PodaciOIgracima[4]; //Cuvaju se pristigli podaci

                //Primanje broja igraca u sobi
                int brojIgraca = (int) in.readObject();

                String ime, boja;

                //Primanje imena i boja svih igraca i cuvanje u lokalnoj promenljivoj podaciOIgracima
                for(int i = 0; i < brojIgraca; i++){
                    ime = (String) in.readObject();
                    boja = (String) in.readObject();
                    podaciOIgracima[i] = new PodaciOIgracima(ime, boja);
                }

                //Cuvanje ucitanih podataka u globalnoj promenljivi klase
                trenutniPodaciOIgracima = podaciOIgracima;

                //Personalizacija gui-a za igraca
                //Ucitavanje scene
                FXMLLoader fxmlLoader = new FXMLLoader(Igra.class.getResource("scene/soba.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                //Dobijanje kontrolora za scenu
                SobaKontroler sobaKontroler = (SobaKontroler) fxmlLoader.getController();
                //Postavljanje globalne promenjive u klasi PocetnaKontroler da ne bi bilo null pri daljem vrsenju
                PocetnaKontroler.sobaKontroler = sobaKontroler;

                //U zavisnosti koji se po redu igrac prikljucuje daju mu se odredjena polja za promenu imena
                switch (brojIgraca){
                    case 4:
                        sobaKontroler.button4.setVisible(true);
                        sobaKontroler.textFieldIme4.setEditable(true);
                        break;
                    case 3:
                        sobaKontroler.button3.setVisible(true);
                        sobaKontroler.textFieldIme3.setEditable(true);
                        break;
                    case 2:
                        sobaKontroler.button2.setVisible(true);
                        sobaKontroler.textFieldIme2.setEditable(true);
                        break;
                }
                //Upisivanje podataka u polja
                switch (brojIgraca){
                    case 4:
                        sobaKontroler.textFieldIme4.setText(trenutniPodaciOIgracima[3].ime);
                    case 3:
                        sobaKontroler.textFieldIme3.setText(trenutniPodaciOIgracima[2].ime);
                    case 2:
                        sobaKontroler.textFieldIme2.setText(trenutniPodaciOIgracima[1].ime);
                        sobaKontroler.textFieldIme1.setText(trenutniPodaciOIgracima[0].ime);
                        break;
                }
                //Postavljanje labela za broj sobe
                sobaKontroler.postaviBrojSobe(text);
                //Prikaz scene
                Igra.stage.setScene(scene);
                Igra.stage.show();

                //Pokretanje niti za slusanje azuriranja za sa promenom imena
                SobaInfoNit sobaInfoNit = new SobaInfoNit(out, in);
                sobaInfoNit.start();

                //TODO: Doraditi pravi gui za svakog igraca
            } else{
                AlertBox.display("Povratna poruka", "Soba ne postoji ili je puna");
            }
        } catch (IOException e) {
            System.out.println("Neki io exception");
//TODO: dodati izlaz
        } catch(ClassNotFoundException e){
            System.out.println("Nije poslat pravi podatak");
        }
    }

}
