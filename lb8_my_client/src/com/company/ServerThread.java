package com.company;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket socket;
    private PrintWriter writer;
    private boolean running; // logout errors without running variable?

    public ServerThread(int port) {
        try {
            socket = new Socket("localhost", port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            writer = new PrintWriter(outputStream, true);
            running = true;
            while (running) {
                String message = reader.readLine(); // client receive message
                runCommand(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void send(String message) {
        writer.println(message);
    }

    public void login(String message) {
        send("$login " + message);
    }

    public void logout() {
        running = false;
        send("$logout");
    }

    public void broadcast(String message) {
        send("$broadcast " + message);
    }

    public void list() {
        send("$list");
    }

    public void privateMessage(String message) {
        String[] arr = message.split(" ", 3);
        send("$private " + arr[1] + " " + arr[2]);
    }

    public void runCommand(String message) {
        if (message.startsWith("$login")) {
            String[] arr = message.split(" ");
            System.out.println(arr[1] + " connected");
        } else if (message.startsWith("$logout")) {
            String[] arr = message.split(" ");
            System.out.println(arr[1] + " disconnected");
        } else if (message.startsWith("$broadcast")) {
            String[] arr = message.split(" ", 3);
            System.out.println(arr[1] + ": " + arr[2]);
        } else if (message.startsWith("$list")) {
            String[] arr = message.split(" ");
            for (int i = 1; i < arr.length; i++) {
                System.out.println(arr[i]);
            }
        } else if (message.startsWith("$private")) {
            String[] arr = message.split(" ", 3);
            System.out.println("[private] " + arr[1] + ": " + arr[2]);
        } else if(message.startsWith("$bad_credentials")){
            System.out.println("Invalid login or password!");
            System.exit(0);
        }
    }
}
