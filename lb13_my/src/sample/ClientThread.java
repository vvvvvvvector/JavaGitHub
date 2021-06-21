package sample;

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

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            writer = new PrintWriter(outputStream, true);
            while (true) { // Server receives position from Client
                String message = reader.readLine();
                String[] posArr = message.split(",");
                int row = Integer.parseInt(posArr[0]);
                int column = Integer.parseInt(posArr[1]);
                server.forwardPos(this, row, column);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPos(int x, int y) {
        writer.println(x + "," + y);
    }
}
