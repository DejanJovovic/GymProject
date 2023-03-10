package ftn.com.mojaTeretana.service;

import java.util.List;

import ftn.com.mojaTeretana.model.Sala;

public interface SalaService {
	
	Sala save(Sala sala);
	Sala delete(Long id);
	Sala update(Sala sala);
	Sala findOneById(Long salaId);
	
	List<Sala> findAll();
	

}
