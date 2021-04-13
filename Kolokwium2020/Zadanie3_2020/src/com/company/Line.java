package com.company;

public class Line implements ReadDevice, WriteDevice {
    private String memory;
    private Modem modem;

    public Line(Modem modem) {
        this.modem = modem;
    }

    @Override
    public String read() {
        return memory;
    }

    @Override
    public void write(String str) {
        memory = str;
        modem.call();
    }
}
