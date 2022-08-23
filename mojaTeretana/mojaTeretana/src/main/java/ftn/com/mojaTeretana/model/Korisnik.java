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
    private String uloga;
    private boolean aktivan;
    
    
    public Korisnik() {
        
    }
    
    
    public Korisnik(String korisnickoIme, String email, String lozinka, String ime, String prezime,
    		String datumRodjenja, String adresa, String brojTelefona, ETipKorisnika tipKorisnika, LocalDate datumIVremeRegistracije ) {
    	this.korisnickoIme = korisnickoIme;
    	this.email = email;
    	this.lozinka = lozinka;
    	this.ime = ime;
    	this.prezime = prezime;
    	this.datumRodjenja = datumRodjenja;
    	this.adresa = adresa;
    	this.brojTelefona = brojTelefona;
    	this.tipKorisnika = tipKorisnika;
    	this.datumIVremeRegistracije = datumIVremeRegistracije;
    	
    }
    

    public Korisnik(String korisnickoIme1, String lozinka1, String email1, String ime1,
                    String prezime1, String datumRodjenja1, String adresa1, String brojTelefona1,
                    LocalDate datumIVremeRegistracije1, ETipKorisnika tipKorisnika, String uloga) {
        this.korisnickoIme = korisnickoIme1;
        this.lozinka = lozinka1;
        this.email = email1;
        this.ime = ime1;
        this.prezime = prezime1;
        this.datumRodjenja = datumRodjenja1;
        this.adresa = adresa1;
        this.brojTelefona = brojTelefona1;
        this.datumIVremeRegistracije = datumIVremeRegistracije1;
        this.uloga = uloga;
    }

    public Korisnik(Long id1, String korisnickoIme1, String lozinka1, String email1, String ime1,
                    String prezime1, String datumRodjenja1, String adresa1, String brojTelefona1,
                    LocalDate datumIVremeRegistracije1 , ETipKorisnika tipKorisnika1,  String uloga, boolean aktivan) {
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
        this.uloga = uloga;
        this.aktivan = aktivan;
    }


	@Override
    public String toString() {
        return "Korisnik{" +
                "id=" + id +
                ", korisnickoIme='" + korisnickoIme + '\'' +
                ", lozinka='" + lozinka + '\'' +
                ", email='" + email + '\'' +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", datumRodjenja='" + datumRodjenja + '\'' +
                ", adresa='" + adresa + '\'' +
                ", brojTelefona='" + brojTelefona + '\'' +
                ", datumIVremeRegistracije='" + datumIVremeRegistracije + '\'' +
                ", tipKorisnika=" + tipKorisnika +
                ", uloga=" + uloga +
                ", aktivan=" + aktivan +
                '}';
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

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}

	public boolean isAktivan() {
		return aktivan;
	}


	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}

	
}
