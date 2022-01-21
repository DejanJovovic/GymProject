package com.ftn.mojaTeretana.service;

import com.ftn.mojaTeretana.model.Korisnik;

import java.util.List;

public interface korisnikService  {

    Korisnik findOneById(Long id);
    Korisnik findOne(String korisnickoIme);
    Korisnik findOne(String korisnickoIme, String lozinka);
    List<Korisnik> findAll();
    Korisnik save(Korisnik korisnik);
}
