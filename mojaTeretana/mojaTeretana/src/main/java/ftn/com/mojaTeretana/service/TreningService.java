package ftn.com.mojaTeretana.service;

import ftn.com.mojaTeretana.model.Trening;

import java.util.List;

public interface TreningService {

    List<Trening> findAll();

    Trening findOneById(Long id);

    Trening save(Trening trening);

    Trening update(Trening trening);
    
    Float sum(Long id);
}
