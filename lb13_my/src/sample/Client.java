package sample;

import java.io.IOException;
import java.net.Socket;

public class Client extends Thread {
    private Socket socket;

    public Client(int port, String address) {
        try {
            socket = new Socket(address, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

    }
}
