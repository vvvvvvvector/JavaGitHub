package com.company;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        Server server = new Server(4000);
        server.listen();
    }
}
