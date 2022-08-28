package ftn.com.mojaTeretana.dao;

import java.util.List;

import ftn.com.mojaTeretana.model.Sala;

public interface SalaDAO {

	public int save(Sala sala);
	public int delete(Long id);
	public int update(Sala sala);
	
	
	List<Sala> findAll();
	
	Sala findOneById(Long id);
	
	
}
