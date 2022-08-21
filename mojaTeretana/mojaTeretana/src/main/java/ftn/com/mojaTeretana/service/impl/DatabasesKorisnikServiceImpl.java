package ftn.com.mojaTeretana.service.impl;

import ftn.com.mojaTeretana.dao.KorisnikDAO;
import ftn.com.mojaTeretana.model.Korisnik;
import ftn.com.mojaTeretana.service.korisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DatabasesKorisnikServiceImpl implements korisnikService {

    @Autowired
    private KorisnikDAO korisnikDAO;

    @Override
    public Korisnik findOneById(Long id) {
        return korisnikDAO.findOneById(id);
    }

    @Override
    public Korisnik findOneByUsername(String korisnickoIme) {
        return korisnikDAO.findOneByUsername(korisnickoIme);
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
