package com.example.administrator.claseEntity;

public class Intretinere {
    int Nr_ap;
    String cotaIndivizia,cotaNrPersoane,apa,energieElectrica,total,restanta,dataLuna,dataAn,statusIntretinere;

    public Intretinere(int nr_ap, String cotaIndivizia, String cotaNrPersoane, String apa, String energieElectrica, String total, String restanta, String dataLuna, String dataAn, String statusIntretinere) {
        Nr_ap = nr_ap;
        this.cotaIndivizia = cotaIndivizia;
        this.cotaNrPersoane = cotaNrPersoane;
        this.apa = apa;
        this.energieElectrica = energieElectrica;
        this.total = total;
        this.restanta = restanta;
        this.dataLuna = dataLuna;
        this.dataAn = dataAn;
        this.statusIntretinere = statusIntretinere;
    }

    public int getNr_ap() {
        return Nr_ap;
    }

    public void setNr_ap(int nr_ap) {
        Nr_ap = nr_ap;
    }

    public String getCotaIndivizia() {
        return cotaIndivizia;
    }

    public void setCotaIndivizia(String cotaIndivizia) {
        this.cotaIndivizia = cotaIndivizia;
    }

    public String getCotaNrPersoane() {
        return cotaNrPersoane;
    }

    public void setCotaNrPersoane(String cotaNrPersoane) {
        this.cotaNrPersoane = cotaNrPersoane;
    }

    public String getApa() {
        return apa;
    }

    public void setApa(String apa) {
        this.apa = apa;
    }

    public String getEnergieElectrica() {
        return energieElectrica;
    }

    public void setEnergieElectrica(String energieElectrica) {
        this.energieElectrica = energieElectrica;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getRestanta() {
        return restanta;
    }

    public void setRestanta(String restanta) {
        this.restanta = restanta;
    }

    public String getDataLuna() {
        return dataLuna;
    }

    public void setDataLuna(String dataLuna) {
        this.dataLuna = dataLuna;
    }

    public String getDataAn() {
        return dataAn;
    }

    public void setDataAn(String dataAn) {
        this.dataAn = dataAn;
    }

    public String getStatusIntretinere() {
        return statusIntretinere;
    }

    public void setStatusIntretinere(String statusIntretinere) {
        this.statusIntretinere = statusIntretinere;
    }
}
