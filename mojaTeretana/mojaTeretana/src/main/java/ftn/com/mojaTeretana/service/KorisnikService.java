package ftn.com.mojaTeretana.service;

import ftn.com.mojaTeretana.model.Korisnik;

import java.util.List;

public interface KorisnikService  {

    Korisnik findOneById(Long id);
    Korisnik findOne(String email, String lozinka);
    List<Korisnik> findAll();
    Korisnik save(Korisnik korisnik);
    Korisnik update(Korisnik korisnik);
    Korisnik updateProfil(Korisnik korisnik);
    Korisnik delete(Long id);
}
