package ftn.com.mojaTeretana.model;

public class ClanskaKarta {
	private Long id;
    private int popust;
    private int brojPoena;
    private Korisnik korisnikId;
    private EStatusClanskeKarte status;

    
    public ClanskaKarta(Long id, Korisnik korisnikId, int popust, int brojPoena, EStatusClanskeKarte status) {
    	super();
    	this.id = id;
    	this.korisnikId = korisnikId;
    	this.popust = popust;
    	this.brojPoena = brojPoena;
    	this.status = status;
    }
    
    public ClanskaKarta(Korisnik korisnikId, int popust, int brojPoena, EStatusClanskeKarte status) {
    	super();
      	this.korisnikId = korisnikId;
    	this.popust = popust;
    	this.brojPoena = brojPoena;
    	this.status = status;
    }
    

	@Override
    public String toString() {
        return "ClanskaKarta{" +
        		"id=" + id +
                "popust=" + popust +
                ", brojPoena=" + brojPoena +
                '}';
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
	public EStatusClanskeKarte getStatus() {
		return status;
	}
	public void setStatus(EStatusClanskeKarte status) {
		this.status = status;
	}

	
	
    
    
}
