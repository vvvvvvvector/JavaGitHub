package com.company;

public class Modem {
    private String name;
    private ReadDevice readLine;
    private WriteDevice writeLine;

    public Modem(String name) {
        this.name = name;
    }

    private void readMessage() {
        System.out.println(name + " is reading a message: " + readLine.read());
    }

    public void connect(Modem newModem) {
        this.writeLine = new Line(newModem);
        newModem.readLine = (Line)this.writeLine;
        this.readLine = new Line(this);
        newModem.writeLine = (Line)this.readLine;
    }

    public void writeMessage(String message) {
        writeLine.write(message);
    }

    public void call() {
        readMessage();
    }
}
