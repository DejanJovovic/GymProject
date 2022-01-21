package com.ftn.mojaTeretana.service;

import com.ftn.mojaTeretana.model.Trening;

import java.util.List;

public interface treningService {

    List<Trening> findAll();

    Trening findOneById(Long id);

    Trening save(Trening trening);
}
