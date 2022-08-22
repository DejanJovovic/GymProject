package ftn.com.mojaTeretana.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.com.mojaTeretana.dao.TipTreningaDAO;
import ftn.com.mojaTeretana.model.TipTreninga;
import ftn.com.mojaTeretana.service.TipTreningaService;

@Service
public class DatabaseTipTreningaServiceImpl implements TipTreningaService{

	@Autowired
	private TipTreningaDAO tipTreningaDAO;
	
	@Override
	public List<TipTreninga> findAll(Long id) {
		return tipTreningaDAO.findAll(id);
	}

}
