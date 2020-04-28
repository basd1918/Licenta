package com.example.administrator.claseEntity;

public class Cheltuieli {
    String pretApa,cheltAdministrative,salubritate,energieElectricaHol,salariuAdmin,intretinereAscensor,curatenieScara;
    String luna,an;

    public Cheltuieli(String pretApa, String cheltAdministrative, String salubritate, String energieElectricaHol, String salariuAdmin, String intretinereAscensor, String curatenieScara, String luna, String an) {
        this.pretApa = pretApa;
        this.cheltAdministrative = cheltAdministrative;
        this.salubritate = salubritate;
        this.energieElectricaHol = energieElectricaHol;
        this.salariuAdmin = salariuAdmin;
        this.intretinereAscensor = intretinereAscensor;
        this.curatenieScara = curatenieScara;
        this.luna = luna;
        this.an = an;
    }

    public String getPretApa() {
        return pretApa;
    }

    public void setPretApa(String pretApa) {
        this.pretApa = pretApa;
    }

    public String getCheltAdministrative() {
        return cheltAdministrative;
    }

    public void setCheltAdministrative(String cheltAdministrative) {
        this.cheltAdministrative = cheltAdministrative;
    }

    public String getSalubritate() {
        return salubritate;
    }

    public void setSalubritate(String salubritate) {
        this.salubritate = salubritate;
    }

    public String getEnergieElectricaHol() {
        return energieElectricaHol;
    }

    public void setEnergieElectricaHol(String energieElectricaHol) {
        this.energieElectricaHol = energieElectricaHol;
    }

    public String getSalariuAdmin() {
        return salariuAdmin;
    }

    public void setSalariuAdmin(String salariuAdmin) {
        this.salariuAdmin = salariuAdmin;
    }

    public String getIntretinereAscensor() {
        return intretinereAscensor;
    }

    public void setIntretinereAscensor(String intretinereAscensor) {
        this.intretinereAscensor = intretinereAscensor;
    }

    public String getCuratenieScara() {
        return curatenieScara;
    }

    public void setCuratenieScara(String curatenieScara) {
        this.curatenieScara = curatenieScara;
    }

    public String getLuna() {
        return luna;
    }

    public void setLuna(String luna) {
        this.luna = luna;
    }

    public String getAn() {
        return an;
    }

    public void setAn(String an) {
        this.an = an;
    }
}
