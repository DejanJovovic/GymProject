package ftn.com.mojaTeretana.service;

import java.util.List;

import ftn.com.mojaTeretana.model.TerminTreninga;

public interface TerminTreningaService {

	TerminTreninga save(TerminTreninga termin);
	TerminTreninga findOneById(Long id);
	
	List<TerminTreninga> findAll(Long id);
	List<TerminTreninga> checkifExists(Long id);
}
