package ftn.com.mojaTeretana.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.com.mojaTeretana.dao.SalaDAO;
import ftn.com.mojaTeretana.model.Sala;
import ftn.com.mojaTeretana.service.SalaService;

@Service
public class DatabaseSalaServiceImpl implements SalaService{

	@Autowired
	private SalaDAO salaDAO;
	
	
	
	@Override
	public Sala save(Sala sala) {
		salaDAO.save(sala);
		return sala;
	}

	@Override
	public Sala delete(Long id) {
		Sala sala = salaDAO.findOneById(id);
		if(sala != null) {
			salaDAO.delete(id);
		}
		return sala;
	}

	@Override
	public Sala update(Sala sala) {
		salaDAO.update(sala);
		return sala;
	}

	@Override
	public Sala findOneById(Long salaId) {
		return salaDAO.findOneById(salaId);
	}

	@Override
	public List<Sala> findAll() {
		return salaDAO.findAll();	
	}

}
