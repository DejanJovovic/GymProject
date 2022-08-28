package ftn.com.mojaTeretana.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import ftn.com.mojaTeretana.dao.KorisnikDAO;
import ftn.com.mojaTeretana.dao.KorpaDAO;
import ftn.com.mojaTeretana.dao.TerminTreningaDAO;
import ftn.com.mojaTeretana.model.Korisnik;
import ftn.com.mojaTeretana.model.Korpa;
import ftn.com.mojaTeretana.model.TerminTreninga;

@Repository
public class KorpaDAOImpl implements KorpaDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private KorisnikDAO korisnikDAO;
	
	@Autowired
	private TerminTreningaDAO terminTreningaDAO;
	
	
	private class KorpaRowCallbackHandler implements RowCallbackHandler {
		private Map<Long, Korpa>korpe = new LinkedHashMap<>();

		@Override
		public void processRow(ResultSet resultSet) throws SQLException {
			int index = 1;
			Long id = resultSet.getLong(index++);
			Long idKorisnika = resultSet.getLong(index++);
			Korisnik korisnik = korisnikDAO.findOneById(idKorisnika);
			Long idTermina = resultSet.getLong(index++);
			TerminTreninga termin = terminTreningaDAO.findOneById(idTermina);
			Korpa korpa = korpe.get(id);
			if(korpa == null) {
				korpa = new Korpa(id, korisnik, termin);
				korpe.put(korpa.getId(), korpa);
			}
					
			
		}
		public List<Korpa> getKorpa() {
			return new ArrayList<>(korpe.values());
		}
	}
	
	
	@Override
	public int save(Korpa korpa) {
		String sql = "Insert into korpe (korisnikId, terminId) VALUES(?, ?)";
		return jdbcTemplate.update(sql, korpa.getKorisnikId().getId(), korpa.getTerminId().getId());
	}

	@Override
	public int deleteZakazane(Long id) {
		String sql = "Delete from korpe where id = ?";
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public Korpa sum(Long id) {
		String sql = "Select sum(cena) from korpe k left join terminTreninga t on k.terminId = t.id left join treninzi tr on t.treningId = tr.id where k.korisnikId = ?";
		KorpaRowCallbackHandler rowCallbackHandler = new KorpaRowCallbackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler, id);
		return rowCallbackHandler.getKorpa().get(0);
	}

	@Override
	public List<Korpa> findKorpa(Long id) {
		String sql = "select * from korpe where korisnikId = ?";
		KorpaRowCallbackHandler rowCallbackHandler = new KorpaRowCallbackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler, id);
		return rowCallbackHandler.getKorpa();		
	}

	@Override
	public Korpa findOne(Long id) {
		String sql = "Select * from korpe where id = ?";
		KorpaRowCallbackHandler rowCallbackHandler = new KorpaRowCallbackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler, id);
		return rowCallbackHandler.getKorpa().get(0);
	}

}
