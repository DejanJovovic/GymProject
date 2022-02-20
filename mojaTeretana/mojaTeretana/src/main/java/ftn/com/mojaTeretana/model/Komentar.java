package ftn.com.mojaTeretana.model;

public class Komentar {

    private long id;
    private String tekstKomentara;
    private int ocena;
    private String datum;
    private String autor;
    private String trening;
    private String statusKomentara;
    private boolean anoniman;

    public Komentar(long id, String tekstKomentara, int ocena, String datumPostavljanja,
                    String autor, String trening, String statusKomentara, boolean anoniman) {
        this.id = id;
        this.tekstKomentara = tekstKomentara;
        this.ocena = ocena;
        this.datum = datumPostavljanja;
        this.autor = autor;
        this.trening = trening;
        this.statusKomentara = statusKomentara;
        this.anoniman = anoniman;
    }

    @Override
    public String toString() {
        return "Komentar{" +
                "id=" + id +
                ", tekstKomentara='" + tekstKomentara + '\'' +
                ", ocena=" + ocena +
                ", datumPostavljanja='" + datum + '\'' +
                ", autor=" + autor +
                ", trening=" + trening +
                ", status='" + statusKomentara + '\'' +
                ", anoniman=" + anoniman +
                '}';
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
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
        return datum;
    }
    public void setDatumPostavljanja(String datumPostavljanja) {
        this.datum = datumPostavljanja;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public String getTrening() {
        return trening;
    }
    public void setTrening(String trening) {
        this.trening = trening;
    }
    public String getStatus() {
        return statusKomentara;
    }
    public void setStatus(String statusKomentara) {
        this.statusKomentara = statusKomentara;
    }
    public boolean isAnoniman() {
        return anoniman;
    }
    public void setAnoniman(boolean anoniman) {
        anoniman = anoniman;
    }
}
