package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Server server = new Server(4000);
        server.listen();
    }
}
