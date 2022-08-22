package ftn.com.mojaTeretana.dao;

import java.util.List;

import ftn.com.mojaTeretana.model.Sala;

public interface SalaDAO {

	int save(Sala sala);
	int delete(Long id);
	int update(Sala sala);
	
	
	List<Sala> findAll();
	
	Sala findOneById(Long id);
	
	
}
