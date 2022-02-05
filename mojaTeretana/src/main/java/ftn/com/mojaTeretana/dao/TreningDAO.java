package ftn.com.mojaTeretana.dao;

import ftn.com.mojaTeretana.model.Trening;

import java.util.List;

public interface TreningDAO {
    List<Trening> findAll();

    Trening findOneById(Long id);


    int save(Trening trening);
}