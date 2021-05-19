package com.company;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket socket;
    private PrintWriter writer;

    public ServerThread(int port) {
        try {
            this.socket = new Socket("localhost", port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            this.writer = new PrintWriter(out, true);
            while (true) {
                String message = reader.readLine(); // read messages from server
                System.out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String message) {
        writer.println(message);
    }
}
