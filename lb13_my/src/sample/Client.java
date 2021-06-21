package sample;

import java.io.*;
import java.net.Socket;

public class Client extends Thread {
    private Socket socket;
    private PrintWriter writer;
    private Main main;

    public Client(int port, String address, Main main) {
        this.main = main;
        try {
            socket = new Socket(address, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            writer = new PrintWriter(outputStream, true);
            while (true) { // Client receives position from another Client
                String message = reader.readLine();
                String[] posArr = message.split(",");
                int row = Integer.parseInt(posArr[0]);
                int column = Integer.parseInt(posArr[1]);
                main.setRemoteSymbol(row, column);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPos(int x, int y) {
        writer.println(x + "," + y);
    }
}
