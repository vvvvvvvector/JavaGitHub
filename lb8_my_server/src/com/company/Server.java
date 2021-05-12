package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private ServerSocket serverSocket;
    private List<ClientThread> clients = new ArrayList<>();

    public Server(int port) {
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

    public void broadcastLogin(ClientThread sender, String message) {
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
}
