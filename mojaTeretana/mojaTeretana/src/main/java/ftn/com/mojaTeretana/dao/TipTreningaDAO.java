package ftn.com.mojaTeretana.dao;

import java.util.List;

import ftn.com.mojaTeretana.model.TipTreninga;

public interface TipTreningaDAO {
	
	List<TipTreninga> findAll(Long id);

}
