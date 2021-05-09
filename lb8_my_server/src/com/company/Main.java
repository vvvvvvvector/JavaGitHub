package com.company;

public class Main {

    public static void main(String[] args) {
        Server server = null;
        server = new Server(5000);
        server.listen();
    }
}
