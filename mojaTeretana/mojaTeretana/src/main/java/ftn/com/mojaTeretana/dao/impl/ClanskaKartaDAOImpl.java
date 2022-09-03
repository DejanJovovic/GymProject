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


import ftn.com.mojaTeretana.dao.ClanskaKartaDAO;
import ftn.com.mojaTeretana.dao.KorisnikDAO;
import ftn.com.mojaTeretana.model.ClanskaKarta;
import ftn.com.mojaTeretana.model.EStatusClanskeKarte;
import ftn.com.mojaTeretana.model.Korisnik;

@Repository
public class ClanskaKartaDAOImpl implements ClanskaKartaDAO{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private KorisnikDAO korisnikDAO;
	
	private class ClanskaKartaRowCallbackHandler implements RowCallbackHandler {
		private Map<Long, ClanskaKarta> clanskeKarte = new LinkedHashMap<>();

		@Override
		public void processRow(ResultSet resultSet) throws SQLException {
			int index = 1;
			Long id = resultSet.getLong(index ++);
			Long idKorisnika = resultSet.getLong(index ++);
			Korisnik korisnik = korisnikDAO.findOneById(idKorisnika);
			Integer popust = resultSet.getInt(index++);
			Integer brojPoena = resultSet.getInt(index++);
			EStatusClanskeKarte status = EStatusClanskeKarte.valueOf(resultSet.getString(index++));
			
			ClanskaKarta clanskaKarta = clanskeKarte.get(id);
			if (clanskaKarta == null) {
				clanskaKarta = new ClanskaKarta(id, korisnik, brojPoena, popust, status);
				clanskeKarte.put(clanskaKarta.getId(), clanskaKarta);
			}
			
		}
		public List<ClanskaKarta> getClanskeKarte() {
			return new ArrayList<>(clanskeKarte.values());
			
		}
	}
	
	
	

	@Override
	public int save(ClanskaKarta clanskaKarta) {
		String sql = "INSERT INTO clanskekarte (korisnikId, popust, brojPoena, status) VALUES(?, ?, ?, ?)";
		return jdbcTemplate.update(sql,clanskaKarta.getKorisnikId().getId(), clanskaKarta.getPopust(), clanskaKarta.getBrojPoena(), clanskaKarta.getStatus().toString());
		
	}

	@Override
	public int update(ClanskaKarta clanskaKarta) {
		String sql = "update clanskekarte set popust = ?, brojPoena = ? where id = ?";
		boolean uspesanUpdate = jdbcTemplate.update(sql, clanskaKarta.getPopust(), clanskaKarta.getBrojPoena(), clanskaKarta.getId()) ==1;
		return 0;
	}


	@Override
	public ClanskaKarta findOneById(Long id) {
		String sql = "select * from clanskekarte where id = ?";
		
		ClanskaKartaRowCallbackHandler rowBackHandler = new ClanskaKartaRowCallbackHandler();
		jdbcTemplate.query(sql, rowBackHandler, id);
		
		return rowBackHandler.getClanskeKarte().get(0);
	}

	@Override
	public List<ClanskaKarta> findAll() {
		String sql = "select * from clanskekarte where status = 'CEKANJE'";
		ClanskaKartaRowCallbackHandler rowCallbackHandler = new ClanskaKartaRowCallbackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler);
		
		return rowCallbackHandler.getClanskeKarte();
	}

	@Override
	public int delete(Long id) {
		String sql = "delete from clanskekarte where id = ?";
		return jdbcTemplate.update(sql, id);
		
	}

	@Override
	public int odobri(Long id) {
		String sql = "update clanskekarte set status = 'ODOBREN' where id = ?";
		
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public ClanskaKarta findOdobrena(Long id) {
		String sql = "select * from clanskekarte where korisnikId = ? and status = 'ODOBREN'";
		ClanskaKartaRowCallbackHandler rowBackHandler = new ClanskaKartaRowCallbackHandler();
		jdbcTemplate.query(sql, rowBackHandler, id);
		if(rowBackHandler.getClanskeKarte().size() == 0) {
			return null;
		}
		return rowBackHandler.getClanskeKarte().get(0);
	}
	
	
	
	

}
