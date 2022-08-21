package ftn.com.mojaTeretana.model;


public class Korpa {
	private Long id;
	private Korisnik korisnikId;
	private TerminTreninga terminId;
	

	
	public Korpa(Long id, Korisnik korisnikId, TerminTreninga terminId) {
		
		this.id = id;
		this.korisnikId = korisnikId;
		this.terminId = terminId;
	}
	public Korpa(Korisnik ulogovani, TerminTreninga termin) {
		this.korisnikId = ulogovani;
		this.terminId = termin;
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
	public TerminTreninga getTerminId() {
		return terminId;
	}
	public void setTerminId(TerminTreninga terminId) {
		this.terminId = terminId;
	}
	


}
