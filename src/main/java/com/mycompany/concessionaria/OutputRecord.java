package com.mycompany.concessionaria;

public class OutputRecord {
    private String marca;
    private String modello;
    private String descrizione;

    // Costruttore
    public OutputRecord(String marca, String modello, String descrizione) {
        this.marca = marca;
        this.modello = modello;
        this.descrizione = descrizione;
    }

    // Getters
    public String getMarca() {
        return marca;
    }

    public String getModello() {
        return modello;
    }

    public String getDescrizione() {
        return descrizione;
    }

    @Override
    public String toString() {
        return marca + "," + modello + "," + descrizione;
    }
}
