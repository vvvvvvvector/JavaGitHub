package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server {
    private ServerSocket serverSocket;
    private List<ClientThread> clients = new ArrayList<>();
    private Map<String, String> credentials;

    public Server(int port, Map<String, String> credentials) {
        this.credentials = credentials;
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        while (true) {
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
                System.out.println("Client Connected");
                ClientThread thread = new ClientThread(clientSocket, this);
                clients.add(thread);
                thread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void broadcast(ClientThread sender, String message) {
        for (ClientThread client : clients) {
            if (client != sender) {
                client.send("$broadcast " + sender.getUserName() + " " + message);
            }
        }
    }

    private void broadcastLogin(ClientThread sender, String message) {
        for (ClientThread client : clients) {
            if (client != sender) {
                client.send("$login " + sender.getUserName());
            }
        }
    }

    public void list(ClientThread sender) {
        String result = "$list ";
        for (ClientThread client : clients) {
            result += client.getUserName() + " ";
        }
        sender.send(result);
    }

    public void privateMessage(ClientThread sender, String message) {
        String[] arr = message.split(" ", 3);
        for (ClientThread client : clients) {
            if (client.getUserName().equals(arr[1])) {
                client.send("$private " + sender.getUserName() + " " + arr[2]);
            }
        }
    }

    public void logout(ClientThread sender) {
        clients.remove(sender);
        for (ClientThread client : clients) {
            client.send("$logout " + sender.getUserName());
        }
    }

    public void authorize(ClientThread sender, String message) {
        String[] loginPassword = message.split(" ");
        if (credentials.containsKey(loginPassword[1]) && credentials.get(loginPassword[1]).equals(loginPassword[2])) {
            sender.authorized = true;
            sender.setUserName(loginPassword[1]);
            broadcastLogin(sender, sender.getUserName());
        } else {
            sender.send("$bad_credentials");
            sender.closeSocket();
            clients.remove(sender);
        }
    }
}
