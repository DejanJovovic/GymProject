package ftn.com.mojaTeretana.model;


public class TipTreninga {

    private Long id;
    private String ime;
    private String opis;


    @Override
    public String toString() {
        return "TipTreninga{" +
                "ime='" + ime + '\'' +
                ", opis='" + opis + '\'' +
                '}';
    }

    public TipTreninga() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getIme() {
        return ime;
    }
    public String getOpis() {
        return opis;
    }
    public void setIme(String ime) {
        this.ime = ime;
    }
    public void setOpis(String opis) {
        this.opis = opis;
    }
}
