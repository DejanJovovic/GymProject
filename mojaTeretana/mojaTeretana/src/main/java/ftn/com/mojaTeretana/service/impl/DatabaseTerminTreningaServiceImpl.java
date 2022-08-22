package ftn.com.mojaTeretana.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.com.mojaTeretana.dao.TerminTreningaDAO;
import ftn.com.mojaTeretana.model.TerminTreninga;
import ftn.com.mojaTeretana.service.TerminTreningaService;

@Service
public class DatabaseTerminTreningaServiceImpl implements TerminTreningaService {

	
	@Autowired
	private TerminTreningaDAO terminTreningaDAO;
	
	@Override
	public TerminTreninga save(TerminTreninga termin) {
		terminTreningaDAO.save(termin);
		return termin;
	}

	@Override
	public TerminTreninga findOneById(Long id) {
		return terminTreningaDAO.findOneById(id);
	}

	@Override
	public List<TerminTreninga> findAll(Long id) {
		return terminTreningaDAO.findAll(id);
	}

	@Override
	public List<TerminTreninga> checkifExists(Long id) {
		return terminTreningaDAO.checkIfExists(id);
	}

}
