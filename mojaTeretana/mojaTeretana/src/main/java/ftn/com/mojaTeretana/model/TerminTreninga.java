package ftn.com.mojaTeretana.model;


import java.time.LocalDate;
import java.time.LocalDateTime;

public class TerminTreninga {
	private Long id;
    private Sala salaId;
    private Trening treningId;
    private LocalDate datumTermina;
   
    
    public TerminTreninga(Long id1, Trening treningId, Sala salaId, LocalDate datumTermina) {
    	this.id = id1;
    	this.treningId = treningId;
    	this.salaId = salaId;
    	this.datumTermina = datumTermina;
    	
    }
    
    
    
    public TerminTreninga(Trening treningId, Sala salaId, LocalDate datumTermina ) {
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



	public LocalDate getDatumTermina() {
		return datumTermina;
	}



	public void setDatumTermina(LocalDate datumTermina) {
		this.datumTermina = datumTermina;
	}

}
