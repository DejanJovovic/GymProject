package ftn.com.mojaTeretana.dao;

import java.util.List;

import ftn.com.mojaTeretana.model.ClanskaKarta;
import ftn.com.mojaTeretana.model.EStatusClanskeKarte;

public interface ClanskaKartaDAO {
	
	int save(ClanskaKarta clanskaKarta);
	
	
	List<ClanskaKarta> findByStatus(EStatusClanskeKarte status);
	
	int update(ClanskaKarta clanskaKarta);
	
	
	ClanskaKarta findByKorisnikIdAndStatus(long korisnikId, EStatusClanskeKarte status);
	
	
	ClanskaKarta findById(long id);

}
