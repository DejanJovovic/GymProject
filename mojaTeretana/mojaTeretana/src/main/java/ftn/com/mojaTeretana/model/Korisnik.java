package ftn.com.mojaTeretana.model;

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
    private String datumIVremeRegistracije;
    private String uloga;

    public Korisnik(String korisnickoIme, String lozinka, String email, String ime,
                    String prezime, String datumRodjenja, String adresa, String brojTelefona,
                    String datumIVremeRegistracije, String uloga) {
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.email = email;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.adresa = adresa;
        this.brojTelefona = brojTelefona;
        this.datumIVremeRegistracije = datumIVremeRegistracije;
        this.uloga = uloga;
    }

    public Korisnik(long id, String korisnickoIme, String lozinka, String email, String ime,
                    String prezime, String datumRodjenja, String adresa, String brojTelefona,
                    String datumIVremeRegistracije, String uloga) {
        this.id = id;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.email = email;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.adresa = adresa;
        this.brojTelefona = brojTelefona;
        this.datumIVremeRegistracije = datumIVremeRegistracije;
        this.uloga = uloga;
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
                ", uloga=" + uloga +
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
    public String getDatumIVremeRegistracije() {
        return datumIVremeRegistracije;
    }
    public void setDatumIVremeRegistracije(String datumIVremeRegistracije) {
        this.datumIVremeRegistracije = datumIVremeRegistracije;
    }
    public String getUloga() {
        return uloga;
    }
    public void setUloga(String uloga) {
        this.uloga = uloga;
    }
}
