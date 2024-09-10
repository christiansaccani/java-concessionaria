package com.mycompany.concessionaria;

public class InputRecord {
    private String key;
    private String marca;
    private String modello;
    private String descrizione;

    // Costruttore
    public InputRecord(String key, String marca, String modello, String descrizione) {
        this.key = key;
        this.marca = marca;
        this.modello = modello;
        this.descrizione = descrizione;
    }

    // Getters e Setters
    public String getKey() {
        return key;
    }

    public String getMarca() {
        return marca;
    }

    public String getModello() {
        return modello;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
