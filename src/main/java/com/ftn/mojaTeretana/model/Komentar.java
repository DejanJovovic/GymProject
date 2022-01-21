package com.ftn.mojaTeretana.model;

import com.ftn.mojaTeretana.model.enume.Status;

public class Komentar {

    private String tekstKomentara;
    private int ocena;
    private String datumPostavljanja;
    private Korisnik autor;
    private Trening trening;
    private Status status;
    private boolean Anoniman;

    @Override
    public String toString() {
        return "Komentar{" +
                "tekstKomentara='" + tekstKomentara + '\'' +
                ", ocena=" + ocena +
                ", datumPostavljanja='" + datumPostavljanja + '\'' +
                ", autor=" + autor +
                ", trening=" + trening +
                ", status=" + status +
                ", Anoniman=" + Anoniman +
                '}';
    }
    public String getTekstKomentara() {
        return tekstKomentara;
    }
    public void setTekstKomentara(String tekstKomentara) {
        this.tekstKomentara = tekstKomentara;
    }
    public int getOcena() {
        return ocena;
    }
    public void setOcena(int ocena) {
        this.ocena = ocena;
    }
    public String getDatumPostavljanja() {
        return datumPostavljanja;
    }
    public void setDatumPostavljanja(String datumPostavljanja) {
        this.datumPostavljanja = datumPostavljanja;
    }
    public Korisnik getAutor() {
        return autor;
    }
    public void setAutor(Korisnik autor) {
        this.autor = autor;
    }
    public Trening getTrening() {
        return trening;
    }
    public void setTrening(Trening trening) {
        this.trening = trening;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public boolean isAnoniman() {
        return Anoniman;
    }
    public void setAnoniman(boolean anoniman) {
        Anoniman = anoniman;
    }
}
