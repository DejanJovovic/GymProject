package ftn.com.mojaTeretana.service;

import ftn.com.mojaTeretana.model.Trening;

import java.util.List;

public interface TreningService {

    List<Trening> findAll();

    Trening findOne(Long treningId);

    Trening save(Trening trening);

    Trening update(Trening trening);
    
    Float sum(Long id);
}
