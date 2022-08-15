package ftn.com.mojaTeretana.model;


public class Korpa {
	private Long id;
	private Korisnik korisnik;
	private Termin termin;
	

	
	public Korpa(Long id, Korisnik korisnik, Termin termin) {
		
		this.id = id;
		this.korisnik = korisnik;
		this.termin = termin;
	}
	public Korpa(Korisnik ulogovani, Termin termin) {
		this.korisnik = ulogovani;
		this.termin = termin;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Korisnik getKorisnikId() {
		return korisnik;
	}
	public void setKorisnikId(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
	public Termin getTerminId() {
		return termin;
	}
	public void setTerminId(Termin termin) {
		this.termin = termin;
	}
	


}
