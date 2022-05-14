package com.igrica.coveceneljutiseklijent;

import com.igrica.coveceneljutiseklijent.lib.AlertBox;
import com.igrica.coveceneljutiseklijent.lib.PodaciOIgracima;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerskaNit extends Thread{
    Socket socket;
    public static ObjectOutputStream out;
    public static ObjectInputStream in;
    static int brojSobe;

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
            out.writeObject(1);
            brojSobe = (int) in.readObject();
            System.out.println(brojSobe);
            //TODO: NAPRAVITI GUI ZA POCETAK IGRE
        } catch (IOException e) {
//TODO: dodati izlaz
        } catch (ClassNotFoundException e) {
            System.out.println("Nije poslat pravi podatak");
        }
    }

    public static void pridruziSeSobi(int text){
        try {
            out.writeObject(2);
            out.writeObject(text);
            boolean pridruzio = (boolean) in.readObject();
            if(pridruzio){
                PodaciOIgracima[] podaciOIgracima = new PodaciOIgracima[4];
                //TODO: otkloniti io exception koji se ovde ispod stvara
                for(int i = 0; i < 4; i++){
                    podaciOIgracima[i] = (PodaciOIgracima) in.readObject();
                }
                System.out.println(podaciOIgracima[0].boja);
                //TODO: NAPRAVITI GUI ZA POKRETANJE IGRE
            } else{
                AlertBox.display("Povratna poruka", "Soba nepostoji ili je puna");
            }
        } catch (IOException e) {
            System.out.println("Neki io exception");
//TODO: dodati izlaz
        } catch(ClassNotFoundException e){
            System.out.println("Nije poslat pravi podatak");
        }
    }

}
