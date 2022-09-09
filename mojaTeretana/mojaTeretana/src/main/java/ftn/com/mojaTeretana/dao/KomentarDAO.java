package ftn.com.mojaTeretana.dao;

import ftn.com.mojaTeretana.model.Komentar;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface KomentarDAO {

    List<Komentar> findAllById(Long id);

    List<Komentar> findAll();

    int save(Komentar komentar);
    int delete(Long id);
    int odobreno(Long id);

    Komentar findOne(Long id);
    
    
 
    
}
