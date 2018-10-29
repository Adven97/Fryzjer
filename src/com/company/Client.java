package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import static com.company.Main.setDostepne;
import static com.company.Main.showChangedInfo;
import static com.company.Main.showInfo;

class Client {

    public static void main(String[] args) {

        final String HOST = "127.0.0.1";
        final int PORT = Main.MAIN_PORT;

        try {
            Socket socket = new Socket(HOST, PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner scanner = new Scanner(System.in);
            String line = null;

            showInfo();
            System.out.println("Witamy w systemie rezerwacji Salonu Fryzjerskiego Cięta Riposta :) ");
            System.out.println("Proszę wybrać godzinę");


            while (!"exit".equalsIgnoreCase(line)) {
                line = scanner.nextLine();


                if(Integer.parseInt(line) >=10 && Integer.parseInt(line) <=17) {
                    if (!setDostepne(Integer.parseInt(line))) {
                        out.println(line);
                        out.flush();

                        System.out.println("Wybrałeś godzinę " + line);
                        /// tu damy if elsa
                        System.out.println("Godzina jest dostępna");
                        showChangedInfo();
                    }
                }

                else{
                    System.out.println("Nie rozumiem, proszę podać godzinę - liczbę z przedziału 10 - 17 ");
                }
            }
            scanner.close();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }
}