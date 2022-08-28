package ftn.com.mojaTeretana.service.impl;

import ftn.com.mojaTeretana.dao.KorisnikDAO;
import ftn.com.mojaTeretana.model.Korisnik;
import ftn.com.mojaTeretana.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseKorisnikServiceImpl implements KorisnikService {

    @Autowired
    private KorisnikDAO korisnikDAO;
    
    

    @Override
    public Korisnik findOneById(Long id) {
        return korisnikDAO.findOneById(id);
    }

    @Override
    public Korisnik findOne(String email, String lozinka) {
        return korisnikDAO.findOne(email, lozinka);
    }

    @Override
    public List<Korisnik> findAll() {
        return korisnikDAO.findAll();
    }

    @Override
    public Korisnik save(Korisnik korisnik) {
        korisnikDAO.save(korisnik);
        return korisnik;
    }

	@Override
	public Korisnik update(Korisnik korisnik) {
		korisnikDAO.update(korisnik);
		return korisnik;
	}

	@Override
	public Korisnik updateProfil(Korisnik korisnik) {
		korisnikDAO.updateProfil(korisnik);
		return korisnik;
	}

	@Override
	public Korisnik delete(Long id) {
		Korisnik korisnik = korisnikDAO.findOneById(id);
		if(korisnik == null ) {
			korisnikDAO.delete(id);
		}
		return korisnik;
	}
}
