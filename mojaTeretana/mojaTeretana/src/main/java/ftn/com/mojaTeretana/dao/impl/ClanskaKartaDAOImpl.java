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
			Long korisnikId = resultSet.getLong(index ++);
			Korisnik korisnik = korisnikDAO.findOneById(korisnikId);
			Integer popust = resultSet.getInt(index++);
			Integer brojPoena = resultSet.getInt(index++);
			String statusClanskeKarte = resultSet.getString(index++);
			EStatusClanskeKarte status = EStatusClanskeKarte.valueOf(statusClanskeKarte);
			
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
		String sql = "INSERT INTO clanskeKarte (korisnikId, popust, bodovi, statusClanske) VALUES(?, ?, ?, ?)";
		return jdbcTemplate.update(sql,clanskaKarta.getKorisnikId().getId(), clanskaKarta.getPopust(), clanskaKarta.getBrojPoena(), clanskaKarta.getStatus().toString());
		
	}

	@Override
	public List<ClanskaKarta> findByStatus(EStatusClanskeKarte status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(ClanskaKarta clanskaKarta) {
		String sql = "update clanskeKarte set popust = ?, bodovi = ? where id = ?";
		boolean uspesanUpdate = jdbcTemplate.update(sql, clanskaKarta.getPopust(), clanskaKarta.getBrojPoena(), clanskaKarta.getId()) ==1;
		return 0;
	}

	@Override
	public ClanskaKarta findByKorisnikIdAndStatus(Long korisnikId, EStatusClanskeKarte status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClanskaKarta findById(Long id) {
		String sql = "select * from clanskeKarte where id = ?";
		
		ClanskaKartaRowCallbackHandler rowBackHandler = new ClanskaKartaRowCallbackHandler();
		jdbcTemplate.query(sql, rowBackHandler, id);
		
		return rowBackHandler.getClanskeKarte().get(0);
	}

	@Override
	public List<ClanskaKarta> findAll() {
		String sql = "select * from clanskeKarte where status = 'CEKANJE'";
		ClanskaKartaRowCallbackHandler rowCallbackHandler = new ClanskaKartaRowCallbackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler);
		
		return rowCallbackHandler.getClanskeKarte();
	}

	@Override
	public int delete(Long id) {
		String sql = "delete from clanskeKarte where id = ?";
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public int odobrena(Long id) {
		String sql = "update clanskeKarte set status = 'ODOBREN' where id = ?";
		
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public ClanskaKarta findOdobrena(Long id) {
		String sql = "select * from clanskeKarte where korisnikId = ? and status = 'ODOBREN'";
		ClanskaKartaRowCallbackHandler rowBackHandler = new ClanskaKartaRowCallbackHandler();
		jdbcTemplate.query(sql, rowBackHandler, id);
		if(rowBackHandler.getClanskeKarte().size() == 0) {
			return null;
		}
		return rowBackHandler.getClanskeKarte().get(0);
	}
	
	
	
	

}
