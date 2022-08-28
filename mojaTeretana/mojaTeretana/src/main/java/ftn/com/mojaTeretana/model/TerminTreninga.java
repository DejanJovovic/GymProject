package ftn.com.mojaTeretana.model;

import java.time.LocalDateTime;

public class TerminTreninga {
	private Long id;
    private Sala salaId;
    private Trening treningId;
    private LocalDateTime datumTermina;
   
    
    public TerminTreninga(Long id1, Trening treningId, Sala salaId, LocalDateTime datumTermina ) {
    	this.id = id1;
    	this.treningId = treningId;
    	this.salaId = salaId;
    	this.datumTermina = datumTermina;
    	
    }
    
    
    
    public TerminTreninga(Trening treningId, Sala salaId, LocalDateTime datumTermina ) {
    	this.treningId = treningId;
    	this.salaId = salaId;
    	this.datumTermina = datumTermina;
    	
    }


	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Sala getSalaId() {
		return salaId;
	}



	public void setSalaId(Sala salaId) {
		this.salaId = salaId;
	}



	public Trening getTreningId() {
		return treningId;
	}



	public void setTreningId(Trening treningId) {
		this.treningId = treningId;
	}



	public LocalDateTime getDatumTermina() {
		return datumTermina;
	}



	public void setDatumTermina(LocalDateTime datumTermina) {
		this.datumTermina = datumTermina;
	}

}
