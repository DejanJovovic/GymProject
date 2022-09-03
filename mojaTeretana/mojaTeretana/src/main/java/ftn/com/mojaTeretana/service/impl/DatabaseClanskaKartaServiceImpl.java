package ftn.com.mojaTeretana.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.com.mojaTeretana.dao.ClanskaKartaDAO;
import ftn.com.mojaTeretana.model.ClanskaKarta;
import ftn.com.mojaTeretana.service.ClanskaKartaService;

@Service
public class DatabaseClanskaKartaServiceImpl implements ClanskaKartaService{
	
	@Autowired
	private ClanskaKartaDAO clanskaKartaDAO;
	
	
	

	@Override
	public ClanskaKarta save(ClanskaKarta clanskaKarta) {
		clanskaKartaDAO.save(clanskaKarta);
		return clanskaKarta;
	}

	@Override
	public ClanskaKarta delete(Long id) {
		ClanskaKarta clanskaKarta = clanskaKartaDAO.findOneById(id);
		if(clanskaKarta != null) {
			clanskaKartaDAO.delete(id);
		}
		return clanskaKarta;
	}

	@Override
	public ClanskaKarta update(ClanskaKarta clanskaKarta) {
		clanskaKartaDAO.update(clanskaKarta);
		return clanskaKarta;

	}

	@Override
	public ClanskaKarta odobri(Long id) {
		ClanskaKarta clanskaKarta = clanskaKartaDAO.findOneById(id);
		if(clanskaKarta != null) {
			clanskaKartaDAO.odobri(id);
		}
		return clanskaKarta;
		
	}

	@Override
	public ClanskaKarta findOdobreno(Long id) {
		return clanskaKartaDAO.findOdobrena(id);
	}

	@Override
	public List<ClanskaKarta> findAll() {
		return clanskaKartaDAO.findAll();	
	}

}
