package com.ftn.mojaTeretana.dao;

import com.ftn.mojaTeretana.model.Trening;

import java.util.List;

public interface TreningDAO {
    List<Trening> findAll();

    Trening findOneById(Long id);


    int save(Trening trening);
}
