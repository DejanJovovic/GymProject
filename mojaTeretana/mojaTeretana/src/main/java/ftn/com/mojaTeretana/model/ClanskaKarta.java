package ftn.com.mojaTeretana.model;

public class ClanskaKarta {
    private int popust;
    private int brojPoena;
    private Korisnik korisnik;
    private EStatusClanskeKarte status;

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
	public EStatusClanskeKarte getStatus() {
		return status;
	}
	public void setStatus(EStatusClanskeKarte status) {
		this.status = status;
	}
	public Korisnik getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
	
	
    
    
}
