package ftn.com.mojaTeretana.service.impl;

import ftn.com.mojaTeretana.dao.TreningDAO;
import ftn.com.mojaTeretana.model.Korisnik;
import ftn.com.mojaTeretana.model.Trening;
import ftn.com.mojaTeretana.service.TreningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseTreningServiceImpl implements TreningService {

    @Autowired
    private  TreningDAO treningDAO;

    @Override
    public List<Trening> findAll() {
        return treningDAO.findAll();
    }

    @Override
    public Trening findOneById(Long id) {
        return treningDAO.findOneById(id);
    }

    @Override
    public Trening update(Trening trening) {
        treningDAO.update(trening);
        return trening;
    }

    @Override
    public Trening save(Trening trening) {
        treningDAO.save(trening);
        return trening;
    }

	@Override
	public Float sum(Long id) {
		return treningDAO.sum(id);
	}
}
