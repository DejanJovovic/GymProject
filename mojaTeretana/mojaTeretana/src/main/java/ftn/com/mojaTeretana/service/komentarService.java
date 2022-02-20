package ftn.com.mojaTeretana.service;

import ftn.com.mojaTeretana.model.Komentar;
import ftn.com.mojaTeretana.model.Trening;

import java.util.List;

public interface komentarService {

    List<Komentar> FindAllById(Long id);
    List<Komentar> FindAll();
    Komentar update(Komentar komentar);
    Komentar FindOneById(Long id);
}
