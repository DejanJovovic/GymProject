package ftn.com.mojaTeretana.service;

import ftn.com.mojaTeretana.model.Komentar;
import ftn.com.mojaTeretana.model.Trening;

import java.util.List;

public interface KomentarService {

    List<Komentar> FindAllById(Long id);
    List<Komentar> FindAll();
    
    Komentar save(Komentar komentar);
    Komentar update(Komentar komentar);
    Komentar FindOneById(Long id);
    Komentar delete(Long id);
    Komentar odobreno(Long id);
}
