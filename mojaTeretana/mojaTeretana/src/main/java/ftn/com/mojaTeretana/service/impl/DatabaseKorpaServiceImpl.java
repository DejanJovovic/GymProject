package ftn.com.mojaTeretana.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.com.mojaTeretana.dao.KorpaDAO;
import ftn.com.mojaTeretana.model.Korpa;
import ftn.com.mojaTeretana.service.KorpaService;


@Service
public class DatabaseKorpaServiceImpl implements KorpaService{

	@Autowired
	private KorpaDAO korpaDAO;
	
	
	
	@Override
	public List<Korpa> findKorpa(Long id) {
		return korpaDAO.findKorpa(id);
	}

	@Override
	public Korpa save(Korpa korpa) {
		korpaDAO.save(korpa);
		return korpa;
	}

	@Override
	public Korpa findOneById(Long id) {
		return korpaDAO.findOne(id);
	}

	@Override
	public Korpa delete(Long id) {
		Korpa korpa = korpaDAO.findOne(id);
		if(korpa != null) {
			korpaDAO.deleteZakazane(id);
		}
		return korpa;
	}

	@Override
	public Korpa sum(Long id) {
		return korpaDAO.sum(id);
	}

}
