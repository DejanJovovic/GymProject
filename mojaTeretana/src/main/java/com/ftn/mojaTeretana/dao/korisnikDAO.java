package com.ftn.mojaTeretana.dao;

import com.ftn.mojaTeretana.model.Korisnik;

import java.util.List;

public interface korisnikDAO{

    Korisnik findOneById(Long id);

    Korisnik findOne(String korisnickoIme);

    Korisnik findOne(String korisnickoIme, String lozinka);

    List<Korisnik> findAll();

    int save(Korisnik korisnik);
}
