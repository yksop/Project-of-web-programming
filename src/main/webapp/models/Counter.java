package models;

import java.io.Serializable;

public class Counter implements Serializable {
    private int contatoreVisite;

    public int getContatoreVisite() {
        return contatoreVisite;
    }

    public void setContatoreVisite(int contatoreVisite) {
        this.contatoreVisite = contatoreVisite;
    }
}
