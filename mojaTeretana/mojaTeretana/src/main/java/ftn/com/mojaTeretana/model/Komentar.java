package ftn.com.mojaTeretana.model;

import java.time.LocalDate;

public class Komentar {

    private Long id;
    private String tekstKomentara;
    private int ocena;
    private LocalDate datum;
    private Trening trening;
    private String autor;
    private boolean anoniman;

	private Status status;

    

    public Komentar(Long id1, String tekstKomentara1, int ocena1, LocalDate datum1, Trening trening1,
    		Status status1, String autor1, boolean anoniman1) {
		this.id = id1;
		this.tekstKomentara = tekstKomentara1;
		this.ocena = ocena1;
		this.datum = datum1;
		this.trening = trening1;
		this.status = status1;
		this.autor = autor1;
		this.anoniman = anoniman1;
	}
    
    public Komentar(String tekstKomentara1, int ocena1, LocalDate datum1,Trening trening1,
    		Status status1) {
		this.tekstKomentara = tekstKomentara1;
		this.ocena = ocena1;
		this.datum = datum1;
		this.trening = trening1;
		this.status = status1;
	}
    
    public Komentar(Long id, String autor) {
    	this.id = id;
    	this.autor = autor;
    }
	

	public Komentar(Long id2, String tekstKomentara2, int ocena2, LocalDate datum2, Korisnik ulogovani, Trening trening2,
    		Status status2, boolean anoniman2, String autor2) {
    	this.id = id2;
		this.tekstKomentara = tekstKomentara2;
		this.ocena = ocena2;
		this.datum = datum2;
		this.trening = trening2;
		this.status = status2;
		this.anoniman = anoniman2;
		this.autor = autor2;
	}

	@Override
	public String toString() {
		return "Komentar [id=" + id + ", tekstKomentara=" + tekstKomentara + ", ocena=" + ocena + ", datum=" + datum
				+ ", status=" + status + ", trening=" + trening + ", autor=" + autor +  ", anoniman=" + anoniman +  "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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


	public LocalDate getDatum() {
		return datum;
	}


	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}



	public Trening getTrening() {
		return trening;
	}

	public void setTrening(Trening trening) {
		this.trening = trening;
	}


	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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
			this.anoniman = anoniman;
		}

}
