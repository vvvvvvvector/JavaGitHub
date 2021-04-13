package com.company;

import java.util.Random;

public class Akcje extends PapieryWartosciowe {

    public Akcje(float wartosc, int id) {
        super(wartosc, id);
    }

    @Override
    public void step() {
        switch (new Random().nextInt(2)) {
            case 0:
                setWartosc(getWartosc() + getWartosc() * 0.1f);
                break;
            case 1:
                setWartosc(getWartosc() - getWartosc() * 0.1f);
                break;
        }
    }
}
