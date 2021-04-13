package com.company;

public class Main {

    public static void main(String[] args) {
        var modem1 = new Modem("modem 1");
        var modem2 = new Modem("modem 2");
        modem1.connect(modem2);
        modem1.writeMessage("hello modem2");
    }
}
