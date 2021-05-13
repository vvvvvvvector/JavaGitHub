package com.company;

import java.io.*;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket socket;
    private Server server;
    private PrintWriter writer;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public ClientThread(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            writer = new PrintWriter(outputStream, true);
            while (true) {
                String message = reader.readLine(); // server reads messages/requests from clients
                if (isCommand(message)) {
                    runCommand(message);
                }
            }
        } catch (IOException e) {
            System.out.println(this.userName + " socket closed!");
        }
    }

    public void closeSocket() {
        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String message) {
        writer.println(message);
    }

    public boolean isCommand(String message) {
        return (message != null && !(message.isEmpty()) && message.charAt(0) == '$');
    }

    public void runCommand(String message) {
        if (message.startsWith("$login")) {
            this.userName = message.split(" ")[1];
            server.broadcastLogin(this, this.userName);
        } else if (message.startsWith("$broadcast")) {
            server.broadcast(this, message.split(" ", 2)[1]); //$broadcast message lol - arr[0] - broadcast, arr[1] message lol
        } else if (message.startsWith("$list")) {
            server.list(this);
        } else if (message.startsWith("$private")) {
            server.privateMessage(this, message);
        } else if (message.startsWith("$logout")) {
            closeSocket();
            server.logout(this);
        }
    }
}
