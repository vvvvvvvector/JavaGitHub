package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        ServerThread serverThread = new ServerThread(5000);
        serverThread.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            String message = reader.readLine();
            serverThread.login(message);
            while (true) {
                message = reader.readLine();
                serverThread.broadcast(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
