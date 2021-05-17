package com.company;

public class Main {

    public static void main(String[] args) {
        FileCommanderCLI fileCommanderCLI = new FileCommanderCLI(System.in, System.out);
        fileCommanderCLI.eventLoop();
    }
}
