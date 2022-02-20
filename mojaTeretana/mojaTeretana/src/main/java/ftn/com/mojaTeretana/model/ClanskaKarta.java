package ftn.com.mojaTeretana.model;

public class ClanskaKarta {
    private int popust;
    private int brojPoena;

    @Override
    public String toString() {
        return "ClanskaKarta{" +
                "popust=" + popust +
                ", brojPoena=" + brojPoena +
                '}';
    }
    public int getPopust() {
        return popust;
    }
    public void setPopust(int popust) {
        this.popust = popust;
    }
    public int getBrojPoena() {
        return brojPoena;
    }
    public void setBrojPoena(int brojPoena) {
        this.brojPoena = brojPoena;
    }
}
