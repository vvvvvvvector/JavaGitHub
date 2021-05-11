package com.company;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread{
    private Socket socket;
    private PrintWriter writer;

    public ServerThread(int port){
        try {
            socket = new Socket("localhost", port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        try {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            writer = new PrintWriter(outputStream, true);
            while(true){
                String message = reader.readLine();
                System.out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void send(String message){
        writer.println(message);
    }
}
