package ftn.com.mojaTeretana.model;

public class Sala {
	private Long id;
    private String oznakaSale;
    private int kapacitet;

    
    public Sala() {
    	
    }
    
    public Sala(Long id, int kapacitet, String oznakaSale) {
    	this.id = id;
    	this.kapacitet = kapacitet;
    	this.oznakaSale = oznakaSale;
    	
    }
    
    public Sala(int kapacitet) {
    	this.kapacitet = kapacitet;
    }
    
	@Override
    public String toString() {
        return "Sala{" +
        		"id=" + id +
                "oznakaSale='" + oznakaSale + '\'' +
                ", kapacitet=" + kapacitet +
                '}';
    }
   

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


    public String getOznakaSale() {
        return oznakaSale;
    }
    public void setOznakaSale(String oznakaSale) {
        this.oznakaSale = oznakaSale;
    }
    public int getKapacitet() {
        return kapacitet;
    }
    public void setKapacitet(int kapacitet) {
        this.kapacitet = kapacitet;
    }
}
