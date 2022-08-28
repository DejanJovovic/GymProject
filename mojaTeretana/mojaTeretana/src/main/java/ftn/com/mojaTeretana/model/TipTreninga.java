package ftn.com.mojaTeretana.model;


public class TipTreninga {

    private Long id;
    private Trening treningId;
    private String ime;
    private String opis;

    public TipTreninga() {
    }
    
   
    
    public TipTreninga(Long id, Trening treningId, String ime, String opis) {
    	super();
    	this.id = id;
    	this.treningId = treningId;
    	this.ime = ime;
    	this.opis = opis;
    	
    	
    }
    

    public Trening getTreningId() {
		return treningId;
	}

	public void setTreningId(Trening treningId) {
		this.treningId = treningId;
	}

	public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getIme() {
        return ime;
    }
    public String getOpis() {
        return opis;
    }
    public void setIme(String ime) {
        this.ime = ime;
    }
    public void setOpis(String opis) {
        this.opis = opis;
    }

}
