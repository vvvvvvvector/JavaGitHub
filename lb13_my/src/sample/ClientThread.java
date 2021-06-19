package sample;

import java.net.Socket;

public class ClientThread extends Thread {
    private Socket socket;
    private Server server;

    public ClientThread(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }
}
