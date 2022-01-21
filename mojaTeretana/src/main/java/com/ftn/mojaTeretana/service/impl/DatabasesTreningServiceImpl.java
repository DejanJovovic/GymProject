package com.ftn.mojaTeretana.service.impl;

import com.ftn.mojaTeretana.dao.TreningDAO;
import com.ftn.mojaTeretana.model.Korisnik;
import com.ftn.mojaTeretana.model.Trening;
import com.ftn.mojaTeretana.service.treningService;
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
