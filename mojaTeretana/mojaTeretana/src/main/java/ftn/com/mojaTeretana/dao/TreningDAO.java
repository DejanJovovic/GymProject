package ftn.com.mojaTeretana.dao;

import ftn.com.mojaTeretana.model.Trening;

import java.util.List;

public interface TreningDAO {
    List<Trening> findAll();

    Trening findOne(Long treningId);

    void update(Trening trening);

    public int save(Trening trening);
    
    Float sum(Long id);

}
