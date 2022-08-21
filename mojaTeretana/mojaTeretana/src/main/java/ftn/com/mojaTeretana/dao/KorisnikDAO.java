package ftn.com.mojaTeretana.dao;

import ftn.com.mojaTeretana.model.Korisnik;

import java.util.List;

public interface KorisnikDAO{

    Korisnik findOneById(Long id);

    Korisnik findOneByUsername(String korisnickoIme);

    Korisnik findOne(String korisnickoIme, String lozinka);

    List<Korisnik> findAll();

    int save(Korisnik korisnik);
    int delete(Long id);
    int update(Korisnik korisnik);
    int updateProfil(Korisnik korisnik);
}
