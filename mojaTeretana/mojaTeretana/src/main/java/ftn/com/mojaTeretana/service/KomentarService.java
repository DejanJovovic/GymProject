package ftn.com.mojaTeretana.service;

import ftn.com.mojaTeretana.model.Komentar;
import ftn.com.mojaTeretana.model.Trening;

import java.util.List;

public interface KomentarService {

    List<Komentar> findAllById(Long id);
    List<Komentar> findAll();
    
    Komentar save(Komentar komentar);
    Komentar findOneById(Long id);
    Komentar delete(Long id);
    Komentar odobreno(Long id);
}
