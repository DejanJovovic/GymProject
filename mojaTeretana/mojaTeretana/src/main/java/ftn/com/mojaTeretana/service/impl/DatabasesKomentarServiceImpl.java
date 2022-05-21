package ftn.com.mojaTeretana.service.impl;

import ftn.com.mojaTeretana.dao.KomentarDAO;
import ftn.com.mojaTeretana.model.Komentar;
import ftn.com.mojaTeretana.service.komentarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DatabasesKomentarServiceImpl implements komentarService {

    @Autowired
    private KomentarDAO komentarDAO;

    @Override
    public List<Komentar> FindAllById(Long id) {
        return komentarDAO.FindAllById(id);
    }

    @Override
    public List<Komentar> FindAll() {
        return komentarDAO.FindAll();
    }

    @Override
    public Komentar update(Komentar komentar) {
        komentarDAO.update(komentar);
        return komentar;
    }

    public Komentar FindOneById(Long id){
        return komentarDAO.FindOneById(id);
    }
}
