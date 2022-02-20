package ftn.com.mojaTeretana.dao;

import ftn.com.mojaTeretana.model.Komentar;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface komentarDAO {

    List<Komentar> FindAllById(Long id);

    List<Komentar> FindAll();

    void update(Komentar komentar);

    Komentar FindOneById(Long id);
}
