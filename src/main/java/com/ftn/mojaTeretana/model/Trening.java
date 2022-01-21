package com.ftn.mojaTeretana.model;

public class Trening {

    private Long id;
    private String naziv;
    private String trener;
    private String kratkiOpis;
    private String slika;
    private TipTreninga tipTreninga;
    private int cena;
    private String vrstaTreninga;
    private String nivoTreninga;
    private int trajanjeTreninga;
    private int prosecnaOcena;

    @Override
    public String toString() {
        return "Trening{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", trener='" + trener + '\'' +
                ", kratkiOpis='" + kratkiOpis + '\'' +
                ", slika='" + slika + '\'' +
                ", cena=" + cena +
                ", tipTreninga=" + tipTreninga +
                ", vrstaTreninga=" + vrstaTreninga +
                ", nivoTreninga=" + nivoTreninga +
                ", trajanjeTreninga=" + trajanjeTreninga +
                ", prosecnaOcena=" + prosecnaOcena +
                '}';
    }

    public Trening() {
    }

    public Trening(String naziv, String slika, int cena, int prosecnaOcena) {
        this.naziv = naziv;
        this.slika = slika;
        this.cena = cena;
        this.prosecnaOcena = prosecnaOcena;
    }
    public Trening(Long id, String naziv, String kratkiOpis, String slika,TipTreninga tipTreninga,
                   int cena, String vrstaTreninga, String nivoTreninga,
                   int trajanjeTreninga, int prosecnaOcena, String trener) {
        this.id = id;
        this.naziv = naziv;
        this.kratkiOpis = kratkiOpis;
        this.slika = slika;
        this.tipTreninga = tipTreninga;
        this.cena = cena;
        this.vrstaTreninga = vrstaTreninga;
        this.nivoTreninga = nivoTreninga;
        this.trajanjeTreninga = trajanjeTreninga;
        this.prosecnaOcena = prosecnaOcena;
        this.trener = trener;
    }
    public Trening(String naziv, String kratkiOpis, String slika,TipTreninga tipTreninga,
                   int cena, String vrstaTreninga, String nivoTreninga,
                   int trajanjeTreninga, int prosecnaOcena, String trener) {
        this.naziv = naziv;
        this.kratkiOpis = kratkiOpis;
        this.slika = slika;
        this.tipTreninga = tipTreninga;
        this.cena = cena;
        this.vrstaTreninga = vrstaTreninga;
        this.nivoTreninga = nivoTreninga;
        this.trajanjeTreninga = trajanjeTreninga;
        this.prosecnaOcena = prosecnaOcena;
        this.trener = trener;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public TipTreninga getTipTreninga() {
        return tipTreninga;
    }
    public void setTipTreninga(TipTreninga tipTreninga) {
        this.tipTreninga = tipTreninga;
    }
    public String getNaziv() {
        return naziv;
    }
    public String getTrener() {
        return trener;
    }
    public String getKratkiOpis() {
        return kratkiOpis;
    }
    public String getSlika() {
        return slika;
    }
    public int getCena() {
        return cena;
    }
    public String getVrstaTreninga() {
        return vrstaTreninga;
    }
    public String getNivoTreninga() {
        return nivoTreninga;
    }
    public int getTrajanjeTreninga() {
        return trajanjeTreninga;
    }
    public int getProsecnaOcena() {
        return prosecnaOcena;
    }
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    public void setTrener(String trener) {
        this.trener = trener;
    }
    public void setKratkiOpis(String kratkiOpis) {
        this.kratkiOpis = kratkiOpis;
    }
    public void setSlika(String slika) {
        this.slika = slika;
    }
    public void setCena(int cena) {
        this.cena = cena;
    }
    public void setVrstaTreninga(String vrstaTreninga) {
        this.vrstaTreninga = vrstaTreninga;
    }
    public void setNivoTreninga(String nivoTreninga) {
        this.nivoTreninga = nivoTreninga;
    }
    public void setTrajanjeTreninga(int trajanjeTreninga) {
        this.trajanjeTreninga = trajanjeTreninga;
    }
    public void setProsecnaOcena(int prosecnaOcena) {
        this.prosecnaOcena = prosecnaOcena;
    }
}
