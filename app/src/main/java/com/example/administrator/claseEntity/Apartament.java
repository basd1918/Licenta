package com.example.administrator.claseEntity;

public class Apartament {
    private  int Nr_ap;
    private String Nume,Email,Suprafata;
    private int Nr_pers;

    public Apartament(int nr_ap, String nume, String email, String suprafata, int nr_pers) {
        Nr_ap = nr_ap;
        Nume = nume;
        Email = email;
        Suprafata = suprafata;
        Nr_pers = nr_pers;
    }

    public int getNr_ap() {
        return Nr_ap;
    }

    public void setNr_ap(int nr_ap) {
        Nr_ap = nr_ap;
    }

    public String getNume() {
        return Nume;
    }

    public void setNume(String nume) {
        Nume = nume;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSuprafata() {
        return Suprafata;
    }

    public void setSuprafata(String suprafata) {
        Suprafata = suprafata;
    }

    public int getNr_pers() {
        return Nr_pers;
    }

    public void setNr_pers(int nr_pers) {
        Nr_pers = nr_pers;
    }
}

