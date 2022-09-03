package ftn.com.mojaTeretana.dao;

import java.util.List;

import ftn.com.mojaTeretana.model.ClanskaKarta;
import ftn.com.mojaTeretana.model.EStatusClanskeKarte;

public interface ClanskaKartaDAO {
	
	int save(ClanskaKarta clanskaKarta);
	
	
	
	List<ClanskaKarta> findAll();
	int update(ClanskaKarta clanskaKarta);
	int delete(Long id);
	int odobri(Long id);
	
	
	ClanskaKarta findOneById(Long id);

	ClanskaKarta findOdobrena(Long id);
}
