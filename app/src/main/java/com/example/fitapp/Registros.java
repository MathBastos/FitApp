package com.example.fitapp;

import java.util.Date;

public class Registros {

    private String peso;
    private String data;

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Registros(String peso, String data) {
        this.peso = peso;
        this.data = data;
    }

    public Registros(){

    }
}
