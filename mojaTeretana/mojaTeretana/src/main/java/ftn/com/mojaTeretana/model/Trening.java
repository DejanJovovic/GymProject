package ftn.com.mojaTeretana.model;

public class Trening {

    private Long id;
    private String naziv;
    private String trener;
    private String kratakOpis;
    private TipTreninga tipTreninga;
    private float cena;
	private EVrstaTreninga vrstaTreninga;
    private ENivoTreninga nivoTreninga;
    private int trajanjeTreninga;
    private int prosecnaOcena;

    
  
    public Trening(String naziv2, String kratakOpis2, int cena2,EVrstaTreninga vrstaTreninga2
    		,ENivoTreninga nivoTreninga2, String trener2, int trajanjeTreninga2,  int prosecnaOcena2) {
        this.naziv = naziv2;
        this.kratakOpis = kratakOpis2;
        this.cena = cena2;
        this.vrstaTreninga = vrstaTreninga2;
        this.nivoTreninga = nivoTreninga2;
        this.trajanjeTreninga = trajanjeTreninga2;
        this.trener = trener2;
        this.prosecnaOcena = prosecnaOcena2;
       
        
    }
    public Trening(Long id1, String naziv1, String kratakOpis1,
    				float cena1, EVrstaTreninga vrstaTreninga1, ENivoTreninga nivoTreninga1,
                   int trajanjeTreninga1, int prosecnaOcena1, String trener) {
        this.id = id1;
        this.naziv = naziv1;
        this.kratakOpis = kratakOpis1;
        this.cena = cena1;
        this.vrstaTreninga = vrstaTreninga1;
        this.nivoTreninga = nivoTreninga1;
        this.trajanjeTreninga = trajanjeTreninga1;
        this.prosecnaOcena = prosecnaOcena1;
        this.trener = trener;
      
    }
    public Trening(String naziv1, String kratakOpis1,TipTreninga tipTreninga1,
                   float cena1, EVrstaTreninga vrstaTreninga1, ENivoTreninga nivoTreninga1,
                   int trajanjeTreninga1, int prosecnaOcena1, String trener) {
        this.naziv = naziv1;
        this.kratakOpis = kratakOpis1;
        this.tipTreninga = tipTreninga1;
        this.cena = cena1;
        this.vrstaTreninga = vrstaTreninga1;
        this.nivoTreninga = nivoTreninga1;
        this.trajanjeTreninga = trajanjeTreninga1;
        this.prosecnaOcena = prosecnaOcena1;
        this.trener = trener;
    }
    
    public Trening(float cena) {
    	this.cena = cena;
    }
    
    public Trening(Long id1) {
    	this.id = id1;
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


	public TipTreninga getTipTreninga() {
		return tipTreninga;
	}

	public void setTipTreninga(TipTreninga tipTreninga) {
		this.tipTreninga = tipTreninga;
	}

	public float getCena() {
		return cena;
	}

	public void setCena(float cena) {
		this.cena = cena;
	}

	public EVrstaTreninga getVrstaTreninga() {
		return vrstaTreninga;
	}

	public void setVrstaTreninga(EVrstaTreninga vrstaTreninga) {
		this.vrstaTreninga = vrstaTreninga;
	}

	public ENivoTreninga getNivoTreninga() {
		return nivoTreninga;
	}

	public void setNivoTreninga(ENivoTreninga nivoTreninga) {
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