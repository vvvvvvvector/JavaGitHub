package sample;

import javafx.application.Platform;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// listen() == run()
public class Server extends Thread { // server.start() in Main blocks main thread -> Server extends Thread
    private ServerSocket serverSocket;
    private ClientThread[] clients = new ClientThread[2];
    private Main main;

    public Server(int port, Main main) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.main = main;
    }

    @Override
    public void run() {
        for (int i = 0; i < 2; ++i) {
            try {
                Socket clientSocket = serverSocket.accept();
                clients[i] = new ClientThread(clientSocket, this);
                clients[i].start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Clients Connected\nGame Started");
        Platform.runLater(() -> main.showGameWidget("Server"));
    }
}
