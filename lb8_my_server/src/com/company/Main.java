package com.company;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Server server = null;
        try {
            server = new Server(5000, getCredentialsFromFile(new File("D:\\javaUniGitHub\\lb8_my_server\\Files\\userDate.txt")));
            server.listen();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, String> getCredentialsFromFile(File file) throws FileNotFoundException {
        Map<String, String> credentials = new HashMap<>();
        Scanner scanner = new Scanner(file); // Scanner > BuffreredReader
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] loginPassword = line.split(" ");
            credentials.put(loginPassword[0], loginPassword[1]);
        }
        return credentials;
    }
}
