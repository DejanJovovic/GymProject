package ftn.com.mojaTeretana.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import ftn.com.mojaTeretana.dao.TerminTreningaDAO;
import ftn.com.mojaTeretana.dao.TreningDAO;
import ftn.com.mojaTeretana.model.Sala;
import ftn.com.mojaTeretana.model.TerminTreninga;
import ftn.com.mojaTeretana.model.Trening;
import ftn.com.mojaTeretana.service.SalaService;

@Repository
public class TerminTreningaDAOImpl implements TerminTreningaDAO{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private TreningDAO treningDAO;
	
	
	@Autowired
	private SalaService salaService;
	
	
	private class TerminTreningaRowCallbackHandler implements RowCallbackHandler {

		
		private Map<Long, TerminTreninga> termini = new LinkedHashMap<>();
		
		@Override
		public void processRow(ResultSet resultSet) throws SQLException {
			int index = 1;
			Long id = resultSet.getLong(index++);
			LocalDate datumTermina = resultSet.getTimestamp(index++).toLocalDateTime().toLocalDate();
			TerminTreninga termin = termini.get(id);
			Long treningId = resultSet.getLong(index++);
			Trening trening = treningDAO.findOne(treningId);
			Long salaId = resultSet.getLong(index++);
			Sala sala = salaService.findOneById(salaId);
	
			if(termin == null) {
				termin = new TerminTreninga(id, trening, sala, datumTermina);
				termini.put(termin.getId(), termin);
			}
			
		}
		public List<TerminTreninga> getTermini() {
			return new ArrayList<>(termini.values());
		}
		
	}
	

	@Override
	public int save(TerminTreninga termin) {
		String sql = "Insert into termintreninga (treningId, salaId, datumTermina) Values(?, ?, ?)";
		return jdbcTemplate.update(sql, termin.getTreningId().getId(), termin.getSalaId().getId(), termin.getDatumTermina());
	}


	@Override
	public List<TerminTreninga> findAll(Long id) {
		String sql = "Select * from termintreninga where treningId = ?";
		TerminTreningaRowCallbackHandler rowCallbackHandler = new TerminTreningaRowCallbackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler, id);
		return rowCallbackHandler.getTermini();
		
	}


	@Override
	public TerminTreninga findOneById(Long id) {
		String sql = "Select * from termintreninga where id = ?";
		TerminTreningaRowCallbackHandler rowCallbackHandler = new TerminTreningaRowCallbackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler, id);
		return rowCallbackHandler.getTermini().get(0);
	}


	@Override
	public List<TerminTreninga> checkIfExists(Long id) {
		String sql = "Select * from termintreninga where salaId = ?";
		TerminTreningaRowCallbackHandler rowCallbackHandler = new TerminTreningaRowCallbackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler, id);
		return rowCallbackHandler.getTermini();
	}
}