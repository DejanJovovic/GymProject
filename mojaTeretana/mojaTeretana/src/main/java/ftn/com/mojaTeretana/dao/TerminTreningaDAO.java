package ftn.com.mojaTeretana.dao;

import java.util.List;

import ftn.com.mojaTeretana.model.TerminTreninga;

public interface TerminTreningaDAO {
	
	
	int save(TerminTreninga termin);
	
	List<TerminTreninga> findAll(Long id);
	
	TerminTreninga findOneById(Long id);
	
	List<TerminTreninga> checkIfExists(Long id);

}
