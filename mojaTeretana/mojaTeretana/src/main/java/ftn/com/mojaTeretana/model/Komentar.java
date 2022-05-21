package ftn.com.mojaTeretana.model;

public class Komentar {

    private long id;
    private String tekstKomentara;
    private int ocena;
    private String datum;
    private String autor;
    private Trening trening;
    private EStatusKomentara statusKomentara;
    private boolean anoniman;

    
    
   

    public Komentar(long id, String tekstKomentara, int ocena, String datum, String autor, Trening trening,
			EStatusKomentara statusKomentara, boolean anoniman) {
		super();
		this.id = id;
		this.tekstKomentara = tekstKomentara;
		this.ocena = ocena;
		this.datum = datum;
		this.autor = autor;
		this.trening = trening;
		this.statusKomentara = statusKomentara;
		this.anoniman = anoniman;
	}
	@Override
    public String toString() {
        return "Komentar{" +
                "id=" + id +
                ", tekstKomentara='" + tekstKomentara + '\'' +
                ", ocena=" + ocena +
                ", datumPostavljanja='" + datum + '\'' +
                ", autor=" + autor +
                ", trening=" + trening +
                ", status='" + statusKomentara + '\'' +
                ", anoniman=" + anoniman +
                '}';
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTekstKomentara() {
        return tekstKomentara;
    }
    public void setTekstKomentara(String tekstKomentara) {
        this.tekstKomentara = tekstKomentara;
    }
    public int getOcena() {
        return ocena;
    }
    public void setOcena(int ocena) {
        this.ocena = ocena;
    }
    public String getDatumPostavljanja() {
        return datum;
    }
    public void setDatumPostavljanja(String datumPostavljanja) {
        this.datum = datumPostavljanja;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    public boolean isAnoniman() {
        return anoniman;
    }
    public void setAnoniman(boolean anoniman) {
        anoniman = anoniman;
    }
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public Trening getTrening() {
		return trening;
	}
	public void setTrening(Trening trening) {
		this.trening = trening;
	}
	public EStatusKomentara getStatusKomentara() {
		return statusKomentara;
	}
	public void setStatusKomentara(EStatusKomentara statusKomentara) {
		this.statusKomentara = statusKomentara;
	}
    
    
    
    
    
    
}
