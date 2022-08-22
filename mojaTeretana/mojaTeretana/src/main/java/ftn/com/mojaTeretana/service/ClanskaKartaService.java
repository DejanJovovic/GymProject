package ftn.com.mojaTeretana.service;

import java.util.List;

import ftn.com.mojaTeretana.model.ClanskaKarta;

public interface ClanskaKartaService {
	
	ClanskaKarta save(ClanskaKarta clanskaKarta);
	ClanskaKarta delete(Long id);
	ClanskaKarta update(ClanskaKarta clanskaKarta);
	ClanskaKarta odobri(Long id);
	ClanskaKarta findOdobreno(Long id);
	
	List<ClanskaKarta> findAll();

}
