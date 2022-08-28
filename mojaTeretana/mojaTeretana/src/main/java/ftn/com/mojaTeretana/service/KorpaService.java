package ftn.com.mojaTeretana.service;

import java.util.List;

import ftn.com.mojaTeretana.model.Korpa;

public interface KorpaService {
	
	List<Korpa> findKorpa(Long id);
	
	Korpa save(Korpa korpa);
	Korpa findOneById(Long id);
	Korpa deleteZakazano(Long id);
	Korpa sum(Long id);

}
