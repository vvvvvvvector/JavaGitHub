package com.company;

import java.io.IOException;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket socket;
    private FileCommanderCLI prompt;

    public ClientThread(Socket socket) {
        this.socket = socket;
        try {
            prompt = new FileCommanderCLI(socket.getInputStream(), socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        prompt.eventLoop();
    }
}
