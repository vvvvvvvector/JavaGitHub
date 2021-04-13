package com.company;

public class Main {

    public static void main(String[] args) {
        var papieryWartosciowe1 = new Obligacje(50, 3, 10.0f);
        var papieryWartosciowe2 = new Obligacje(60, 4, 20.0f);
        var papieryWartosciowe3 = new Akcje(30, 1);
        var papieryWartosciowe4 = new Akcje(20, 2);
        var papieryWartosciowe5 = new Akcje(100, 5);
        PapieryWartosciowe[] papieryWartosciowe = {papieryWartosciowe1, papieryWartosciowe2, papieryWartosciowe3, papieryWartosciowe4, papieryWartosciowe5};
        Portfel portfel = new Portfel(papieryWartosciowe);
        System.out.println(portfel.getValue());
        portfel.step();
        for (var s:papieryWartosciowe) {
            System.out.println("ID: "+s.getId()+"\nValue: "+s.getWartosc());
        }
        System.out.println(portfel.getValue());
    }
}
