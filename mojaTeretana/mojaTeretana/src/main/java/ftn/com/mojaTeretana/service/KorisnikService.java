package ftn.com.mojaTeretana.service;

import ftn.com.mojaTeretana.model.Korisnik;

import java.util.List;

public interface KorisnikService  {

    Korisnik findOneById(Long id);
    Korisnik findOneByUsername(String korisnickoIme);
    Korisnik findOne(String korisnickoIme, String lozinka);
    List<Korisnik> findAll();
    Korisnik save(Korisnik korisnik);
    Korisnik update(Korisnik korisnik);
    Korisnik updateProfil(Korisnik korisnik);
    Korisnik delete(Long id);
}
