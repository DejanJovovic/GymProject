package ftn.com.mojaTeretana.dao;

import java.util.List;

import ftn.com.mojaTeretana.model.ClanskaKarta;
import ftn.com.mojaTeretana.model.EStatusClanskeKarte;

public interface ClanskaKartaDAO {
	
	int save(ClanskaKarta clanskaKarta);
	
	
	
	List<ClanskaKarta> findAll();
	
	List<ClanskaKarta> findByStatus(EStatusClanskeKarte status);
	
	int update(ClanskaKarta clanskaKarta);
	int delete(Long id);
	int odobrena(Long id);
	
	
	ClanskaKarta findByKorisnikIdAndStatus(Long korisnikId, EStatusClanskeKarte status);
	
	
	ClanskaKarta findById(Long id);

	ClanskaKarta findOdobrena(Long id);
}
