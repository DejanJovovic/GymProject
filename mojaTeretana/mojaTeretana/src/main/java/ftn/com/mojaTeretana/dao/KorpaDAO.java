package ftn.com.mojaTeretana.dao;

import java.util.List;

import ftn.com.mojaTeretana.model.Korpa;

public interface KorpaDAO {
	
	
	int save(Korpa korpa);
	int deleteZakazane(Long id);
	
	Korpa sum(Long id);
	List <Korpa> findKorpa(Long id);
	Korpa findOne(Long id);
	

}
