package ftn.com.mojaTeretana.model;

public class Trening {

    private Long id;
    private String naziv;
    private String trener;
    private String kratakOpis;
    private String slika;
    private TipTreninga tipTreninga;
    private int cena;
    private String vrstaTreninga;
    private String nivoTreninga;
    private int trajanjeTreninga;
    private int prosecnaOcena;

    
    public Trening() {
    }

    public Trening(String naziv1, String kratakOpis1, String slika, int cena1,String vrstaTreninga,String nivoTreninga
    		,int trajanjeTreninga1, String trener,  int prosecnaOcena1) {
        this.naziv = naziv1;
        this.kratakOpis = kratakOpis1;
        this.slika = slika;
        this.cena = cena1;
        this.vrstaTreninga = vrstaTreninga;
        this.nivoTreninga = nivoTreninga;
        this.trajanjeTreninga = trajanjeTreninga1;
        this.trener = trener;
        this.prosecnaOcena = prosecnaOcena1;
    }
    public Trening(Long id1, String naziv1, String kratakOpis1, String slika,TipTreninga tipTreninga1,
                   int cena1, String vrstaTreninga1, String nivoTreninga1,
                   int trajanjeTreninga1, int prosecnaOcena1, String trener) {
        this.id = id1;
        this.naziv = naziv1;
        this.kratakOpis = kratakOpis1;
        this.slika = slika;
        this.tipTreninga = tipTreninga1;
        this.cena = cena1;
        this.vrstaTreninga = vrstaTreninga1;
        this.nivoTreninga = nivoTreninga1;
        this.trajanjeTreninga = trajanjeTreninga1;
        this.prosecnaOcena = prosecnaOcena1;
        this.trener = trener;
    }
    public Trening(String naziv1, String kratakOpis1, String slika,TipTreninga tipTreninga1,
                   int cena1, String vrstaTreninga1, String nivoTreninga1,
                   int trajanjeTreninga1, int prosecnaOcena1, String trener) {
        this.naziv = naziv1;
        this.kratakOpis = kratakOpis1;
        this.slika = slika;
        this.tipTreninga = tipTreninga1;
        this.cena = cena1;
        this.vrstaTreninga = vrstaTreninga1;
        this.nivoTreninga = nivoTreninga1;
        this.trajanjeTreninga = trajanjeTreninga1;
        this.prosecnaOcena = prosecnaOcena1;
        this.trener = trener;
    }
    
    public Trening(int cena) {
    	this.cena = cena;
    }
    
    public Trening(Long id1) {
    	this.id = id1;
    }
    
    @Override
    public String toString() {
        return "Trening{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", trener='" + trener + '\'' +
                ", opis='" + kratakOpis + '\'' +
                ", slika='" + slika + '\'' +
                ", cena=" + cena +
                ", tipTreninga=" + tipTreninga +
                ", vrstaTreninga=" + vrstaTreninga +
                ", nivoTreninga=" + nivoTreninga +
                ", trajanjeTreninga=" + trajanjeTreninga +
                ", prosecnaOcena=" + prosecnaOcena +
                '}';
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getTrener() {
		return trener;
	}

	public void setTrener(String trener) {
		this.trener = trener;
	}

	public String getKratakOpis() {
		return kratakOpis;
	}

	public void setKratakOpis(String kratakOpis) {
		this.kratakOpis = kratakOpis;
	}

	public String getSlika() {
		return slika;
	}

	public void setSlika(String slika) {
		this.slika = slika;
	}

	public TipTreninga getTipTreninga() {
		return tipTreninga;
	}

	public void setTipTreninga(TipTreninga tipTreninga) {
		this.tipTreninga = tipTreninga;
	}

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	public String getVrstaTreninga() {
		return vrstaTreninga;
	}

	public void setVrstaTreninga(String vrstaTreninga) {
		this.vrstaTreninga = vrstaTreninga;
	}

	public String getNivoTreninga() {
		return nivoTreninga;
	}

	public void setNivoTreninga(String nivoTreninga) {
		this.nivoTreninga = nivoTreninga;
	}

	public int getTrajanjeTreninga() {
		return trajanjeTreninga;
	}

	public void setTrajanjeTreninga(int trajanjeTreninga) {
		this.trajanjeTreninga = trajanjeTreninga;
	}

	public int getProsecnaOcena() {
		return prosecnaOcena;
	}

	public void setProsecnaOcena(int prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}

    
    
}