package ftn.com.mojaTeretana.model;

public class Termin {
    private Sala sala;
    private Trening trening;
    private String datumTermina;

    @Override
    public String toString() {
        return "Termin{" +
                "sala=" + sala +
                ", trening=" + trening +
                ", datumTermina='" + datumTermina + '\'' +
                '}';
    }
    public Sala getSala() {
        return sala;
    }
    public void setSala(Sala sala) {
        this.sala = sala;
    }
    public Trening getTrening() {
        return trening;
    }
    public void setTrening(Trening trening) {
        this.trening = trening;
    }
    public String getDatumTermina() {
        return datumTermina;
    }
    public void setDatumTermina(String datumTermina) {
        this.datumTermina = datumTermina;
    }
}
