package ftn.com.mojaTeretana.service.impl;

import ftn.com.mojaTeretana.dao.TreningDAO;
import ftn.com.mojaTeretana.model.Trening;
import ftn.com.mojaTeretana.service.treningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DatabasesTreningServiceImpl implements treningService {

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
    public Trening save(Trening trening) {
        treningDAO.save(trening);
        return trening;
    }
}
