package ftn.com.mojaTeretana.dao;

import java.util.List;

import ftn.com.mojaTeretana.model.Korpa;

public interface KorpaDAO {
	
	
	public int save(Korpa korpa);
	int deleteZakazane(Long id);
	
	public Korpa sum(Long id);
	public List <Korpa> findKorpa(Long id);
	public Korpa findOneById(Long id);
	

}
