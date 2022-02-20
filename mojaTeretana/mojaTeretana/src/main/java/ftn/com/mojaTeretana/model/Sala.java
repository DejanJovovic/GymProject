package ftn.com.mojaTeretana.model;

public class Sala {
    private String oznakaSale;
    private int kapacitet;

    @Override
    public String toString() {
        return "Sala{" +
                "oznakaSale='" + oznakaSale + '\'' +
                ", kapacitet=" + kapacitet +
                '}';
    }
    public String getOznakaSale() {
        return oznakaSale;
    }
    public void setOznakaSale(String oznakaSale) {
        this.oznakaSale = oznakaSale;
    }
    public int getKapacitet() {
        return kapacitet;
    }
    public void setKapacitet(int kapacitet) {
        this.kapacitet = kapacitet;
    }
}
