package ftn.com.mojaTeretana.dao;

import ftn.com.mojaTeretana.model.Komentar;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface KomentarDAO {

    List<Komentar> FindAllById(Long id);

    List<Komentar> FindAll();

    void update(Komentar komentar);
    int save(Komentar komentar);
    int delete(Long id);
    int odobreno(Long id);

    Komentar FindOneById(Long id);
    
    
    List<Komentar> findAllByTreningId(Long treningId);
}
