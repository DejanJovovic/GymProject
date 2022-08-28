package ftn.com.mojaTeretana.model;

import java.time.LocalDate;

public class Korisnik {

    private Long id;
    private String korisnickoIme;
    private String lozinka;
    private String email;
    private String ime;
    private String prezime;
    private String datumRodjenja;
    private String adresa;
    private String brojTelefona;
    private LocalDate datumIVremeRegistracije;
    private ETipKorisnika tipKorisnika;
    private boolean aktivan;
    
    
    public Korisnik() {
        
    }
    
    
    public Korisnik(long id,String korisnickoIme, String email, String ime, String prezime) {
    	this.id = id;
    	this.korisnickoIme = korisnickoIme;
    	this.email = email;
    	this.ime = ime;
    	this.prezime = prezime;
    	
    }
    

    public Korisnik(String korisnickoIme1, String lozinka1, String email1, String ime1,
                    String prezime1, String datumRodjenja1, String adresa1, String brojTelefona1,
                    LocalDate datumIVremeRegistracije1, ETipKorisnika tipKorisnika) {
        this.korisnickoIme = korisnickoIme1;
        this.lozinka = lozinka1;
        this.email = email1;
        this.ime = ime1;
        this.prezime = prezime1;
        this.datumRodjenja = datumRodjenja1;
        this.adresa = adresa1;
        this.brojTelefona = brojTelefona1;
        this.datumIVremeRegistracije = datumIVremeRegistracije1;
    }

    public Korisnik(Long id1, String korisnickoIme1, String lozinka1, String email1, String ime1,
                    String prezime1, String datumRodjenja1, String adresa1, String brojTelefona1,
                    LocalDate datumIVremeRegistracije1 , ETipKorisnika tipKorisnika1, boolean aktivan) {
        this.id = id1;
        this.korisnickoIme = korisnickoIme1;
        this.lozinka = lozinka1;
        this.email = email1;
        this.ime = ime1;
        this.prezime = prezime1;
        this.datumRodjenja = datumRodjenja1;
        this.adresa = adresa1;
        this.brojTelefona = brojTelefona1;
        this.datumIVremeRegistracije = datumIVremeRegistracije1;
        this.tipKorisnika = tipKorisnika1;
        this.aktivan = aktivan;
    }
    
    public Korisnik(long idKorisnika, String korisnickoIme) {
    	this.id = idKorisnika;
    	this.korisnickoIme = korisnickoIme;
    	
    }

    
    

    public Korisnik(Long idKorisnika, String korisnickoIme1, String lozinka1, String email1, String ime1,
                    String prezime1, String datumRodjenja1, String adresa1, String brojTelefona1,
                    LocalDate datumIVremeRegistracije1, ETipKorisnika tipKorisnika) {
        this.id = idKorisnika;
    	this.korisnickoIme = korisnickoIme1;
        this.lozinka = lozinka1;
        this.email = email1;
        this.ime = ime1;
        this.prezime = prezime1;
        this.datumRodjenja = datumRodjenja1;
        this.adresa = adresa1;
        this.brojTelefona = brojTelefona1;
        this.datumIVremeRegistracije = datumIVremeRegistracije1;
    }
    
    public Korisnik(String anoniman) {
    	this.korisnickoIme = anoniman;
    }
    


	@Override
	public String toString() {
		return "Korisnik [korisnickoIme=" + korisnickoIme + "]";
	}

    
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getKorisnickoIme() {
        return korisnickoIme;
    }
    public String getLozinka() {
        return lozinka;
    }
    public String getEmail() {
        return email;
    }
    public String getIme() {
        return ime;
    }
    public String getPrezime() {
        return prezime;
    }
    public String getDatumRodjenja() {
        return datumRodjenja;
    }
    public String getAdresa() {
        return adresa;
    }
    public String getBrojTelefona() {
        return brojTelefona;
    }
    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }
    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setIme(String ime) {
        this.ime = ime;
    }
    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
    public void setDatumRodjenja(String datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }
    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }
    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public LocalDate getDatumIVremeRegistracije() {
		return datumIVremeRegistracije;
	}

	public void setDatumIVremeRegistracije(LocalDate datumIVremeRegistracije) {
		this.datumIVremeRegistracije = datumIVremeRegistracije;
	}

	public ETipKorisnika getTipKorisnika() {
		return tipKorisnika;
	}

	public void setTipKorisnika(ETipKorisnika tipKorisnika) {
		this.tipKorisnika = tipKorisnika;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isAktivan() {
		return aktivan;
	}


	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}

	
}
