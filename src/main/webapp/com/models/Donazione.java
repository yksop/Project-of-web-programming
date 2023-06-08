package com.models;


public class Donazione {
    private int mese;
    private int totale_donazioni;

    public Donazione(int mese, int totale_donazioni) {
        this.mese = mese;
        this.totale_donazioni = totale_donazioni;
    }

    public int getMese() {
        return mese;
    }

    public void setMese(int mese) {
        this.mese = mese;
    }

    public int getTotaleDonazioni() {
        return totale_donazioni;
    }

    public void setTotaleDonazioni(int totale_donazioni) {
        this.totale_donazioni = totale_donazioni;
    }
}
