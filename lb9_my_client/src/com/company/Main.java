package com.company;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ServerThread serverThread = new ServerThread(4000);
        serverThread.start();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String message = scanner.nextLine();
            serverThread.send(message);
        }
    }
}
