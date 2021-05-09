package com.company;

import java.io.*;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket socket;
    private Server server;
    private PrintWriter writer;

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
            String line;
            while((line = reader.readLine()) != null){
                if("quit".equalsIgnoreCase(line)){
                    break;
                }
                String message = "You typed: " + line + "\n";
                outputStream.write(message.getBytes());
                server.broadcast(this, line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void send(String message){
        writer.println(message);
    }
}
