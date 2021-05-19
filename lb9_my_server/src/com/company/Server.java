package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {
    private ServerSocket serverSocket;
    private Vector<ClientThread> clients = new Vector<>();

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
                ClientThread clientThread = new ClientThread(clientSocket);
                clients.add(clientThread);
                clientThread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
