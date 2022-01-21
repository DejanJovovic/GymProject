package com.ftn.mojaTeretana.service.impl;

import com.ftn.mojaTeretana.dao.korisnikDAO;
import com.ftn.mojaTeretana.model.Korisnik;
import com.ftn.mojaTeretana.service.korisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DatabasesKorisnikServiceImpl implements korisnikService {

    @Autowired
    private korisnikDAO korisnikDAO;

    @Override
    public Korisnik findOneById(Long id) {
        return korisnikDAO.findOneById(id);
    }

    @Override
    public Korisnik findOne(String korisnickoIme) {
        return korisnikDAO.findOne(korisnickoIme);
    }

    @Override
    public Korisnik findOne(String korisnickoIme, String lozinka) {
        return korisnikDAO.findOne(korisnickoIme, lozinka);
    }

    @Override
    public List<Korisnik> findAll() {
        return korisnikDAO.findAll();
    }

    @Override
    public Korisnik save(Korisnik korisnik) {
        korisnikDAO.save(korisnik);
        return korisnik;
    }
}
