package com.company;

public class Portfel {
    private PapieryWartosciowe[] papieryWartosciowe;

    public Portfel(PapieryWartosciowe[] papieryWartosciowe) {
        this.papieryWartosciowe = papieryWartosciowe;
    }
    public void step(){
        for (PapieryWartosciowe i : papieryWartosciowe){
            i.step();
        }
    }
    public float getValue(){
        float value = 0.0f;
        for (PapieryWartosciowe i : papieryWartosciowe){
            value += i.getWartosc();
        }
        return value;
    }
}
