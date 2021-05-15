package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {
    private ServerSocket serverSocket;
    private List<ClientThread> clients = new ArrayList<>();
    private Map<String, String> credentials;
    private Map<String, Queue<String>> messageQueues = new HashMap<>();

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
            if (client != sender && client.authorized) {
                client.send("$broadcast " + sender.getUserName() + " " + message);
            }
        }
    }

    private void broadcastLogin(ClientThread sender) {
        for (ClientThread client : clients) {
            if (client != sender && client.authorized) {
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
        boolean inClients = false;
        for (ClientThread client : clients) {
            if (client.getUserName().equals(arr[1])) {
                client.send("$private " + sender.getUserName() + " " + arr[2]);
                inClients = true;
                break;
            }
        }
        if (!inClients) {
            if (credentials.containsKey(arr[1])) { // if Server has that user
                Queue<String> messageQueue;
                if (messageQueues.containsKey(arr[1])) { // if map already has messages for user
                    messageQueue = messageQueues.get(arr[1]); // return existing queue for user
                } else {
                    messageQueue = new LinkedList<String>(); // create queue if it does not exist
                    messageQueues.put(arr[1], messageQueue); // add new pair UserName -> messagesQueue to map
                }
                messageQueue.add("$private " + sender.getUserName() + " " + arr[2]); // add new message to queue
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
            broadcastLogin(sender);
            sendQueuedMessage(sender);
        } else {
            sender.send("$bad_credentials");
            sender.closeSocket();
            clients.remove(sender);
        }
    }

    private void sendQueuedMessage(ClientThread sender) {
        Queue<String> messageQueue = messageQueues.get(sender.getUserName());
        if (messageQueue != null) {
            while (!messageQueue.isEmpty()) {
                String message = messageQueue.remove();
                sender.send(message);
            }
        }
    }
}
