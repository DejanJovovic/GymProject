package ftn.com.mojaTeretana.dao;

import ftn.com.mojaTeretana.model.Korisnik;

import java.util.List;

public interface korisnikDAO{

    Korisnik findOneById(Long id);

    Korisnik findOneByUsername(String korisnickoIme);

    Korisnik findOne(String korisnickoIme, String lozinka);

    List<Korisnik> findAll();

    int save(Korisnik korisnik);
}
