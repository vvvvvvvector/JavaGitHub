package com.company;

public abstract class PapieryWartosciowe {
    private float wartosc;
    private int id;

    public PapieryWartosciowe(float wartosc, int id) {
        this.wartosc = wartosc;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public float getWartosc() {
        return wartosc;
    }

    public void setWartosc(float wartosc) {
        this.wartosc = wartosc;
    }

    public abstract void step();
}
