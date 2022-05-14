package com.igrica.coveceneljutiseklijent;

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
            System.out.println("Doslo je do prekida veze");
        }
    }

    public static void kreirajSobu(){
        try {
            out.writeObject("Poruka.KREIRAJSOBU");
            brojSobe = (int) in.readObject();
            System.out.println(brojSobe);
        } catch (IOException e) {

        } catch (ClassNotFoundException e) {
            System.out.println("Nije poslat broj sobe");
        }
    }

    public static void pridruziSeSobi(String text){
        try {
            out.writeObject(text);
        } catch (IOException e) {

        }
    }

}
