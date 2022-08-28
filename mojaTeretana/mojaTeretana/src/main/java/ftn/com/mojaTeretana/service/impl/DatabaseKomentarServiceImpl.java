package ftn.com.mojaTeretana.service.impl;

import ftn.com.mojaTeretana.dao.KomentarDAO;
import ftn.com.mojaTeretana.model.Komentar;
import ftn.com.mojaTeretana.service.KomentarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseKomentarServiceImpl implements KomentarService {

    @Autowired
    private KomentarDAO komentarDAO;

    
    
    @Override
    public List<Komentar> findAllById(Long id) {
        return komentarDAO.findAllById(id);
    }

    @Override
    public List<Komentar> findAll() {
        return komentarDAO.findAll();
    }

    @Override
    public Komentar update(Komentar komentar) {
        komentarDAO.update(komentar);
        return komentar;
    }

    public Komentar findOneById(Long id){
        return komentarDAO.findOne(id);
    }

	@Override
	public Komentar save(Komentar komentar) {
		komentarDAO.save(komentar);
		return komentar;
	}

	@Override
	public Komentar delete(Long id) {
		Komentar komentar = komentarDAO.findOne(id);
		if(komentar != null) {
			komentarDAO.delete(id);
		}
		return komentar;
	}

	@Override
	public Komentar odobreno(Long id) {
		Komentar komentar = komentarDAO.findOne(id);
		if(komentar != null) {
			komentarDAO.odobreno(id);
		}
		return komentar;
	}
}
