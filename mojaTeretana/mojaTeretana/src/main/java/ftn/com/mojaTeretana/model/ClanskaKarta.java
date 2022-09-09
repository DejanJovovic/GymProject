package ftn.com.mojaTeretana.model;

public class ClanskaKarta {
	private Long id;
    private int popust;
    private int brojPoena;
    private Korisnik korisnikId;
    private StatusClanskeKarte statusC;




	public ClanskaKarta(Long id, Korisnik korisnikId, int popust, int brojPoena, StatusClanskeKarte statusC) {
    	super();
    	this.id = id;
    	this.korisnikId = korisnikId;
    	this.popust = popust;
    	this.brojPoena = brojPoena;
    	this.statusC = statusC;
    	
    }
    
    public ClanskaKarta(Korisnik korisnikId, int popust, int brojPoena, StatusClanskeKarte statusC) {
    	super();
      	this.korisnikId = korisnikId;
    	this.popust = popust;
    	this.brojPoena = brojPoena;
    	this.statusC = statusC;

    }
   
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Korisnik getKorisnikId() {
		return korisnikId;
	}

	public void setKorisnikId(Korisnik korisnikId) {
		this.korisnikId = korisnikId;
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
	public StatusClanskeKarte getStatusC() {
		return statusC;
	}
	public void setStatus(StatusClanskeKarte statusC) {
		this.statusC = statusC;
	}

   
}
