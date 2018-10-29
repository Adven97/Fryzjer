package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    static String line = null;
    static final int MAIN_PORT = 1010;
    public static boolean[] xd = new boolean[20];

    public static void main(String[] args) {

        ServerSocket server = null;

        try {
            server = new ServerSocket(MAIN_PORT);
            server.setReuseAddress(true);

            showInfo();

            while (true) {
                Socket client = server.accept();
             //   ClientHandler clientSock = new ClientHandler(client);

                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                line = in.readLine();

                System.out.println("-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --");
                System.out.println("Klient wybrał godzinę : "+line);

                getWolne(Integer.parseInt(line),false);
                setDostepne(Integer.parseInt(line));
                showChangedInfo();
                // The background thread will handle each client separately
               // new Thread(clientSock).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    static void showInfo(){

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");


        System.out.println("Godziny przyjęć fryzjera w dniu "+ dateFormat.format(new Date()));

        for(int godz =10; godz <=17; godz++) {
                xd[godz] =true;         ///    zawsze zwroci false wiec sie nie wykona
                getWolne(godz,xd[godz]);
          //  } else {
            //    getZajete(godz);
            }
        }

    static void showChangedInfo(){

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        System.out.println("--------------------------------------------------------");
        System.out.println("Godziny przyjęć fryzjera w dniu "+ dateFormat.format(new Date()));

        for(int godz =10; godz <=17; godz++) {
             if(xd[godz])    {
                 getWolne(godz,true);
             }
             else
                 getWolne(godz,false);
        }
    }


    static void getWolne(int godzina, boolean wolne){
        String dostepnosc =null;
        if(wolne) {
            dostepnosc=" Wolne";
        }
        else
            dostepnosc=" Zajęte";

        System.out.println("  "+godzina+" - "+(godzina+1) + dostepnosc );
        //wolny=true;
    }

      static boolean setDostepne(int godz){
        xd[godz]=false;
        return xd[godz];
    }
}
