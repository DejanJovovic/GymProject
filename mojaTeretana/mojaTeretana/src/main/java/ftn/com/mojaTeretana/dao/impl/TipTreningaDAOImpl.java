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

import ftn.com.mojaTeretana.dao.TipTreningaDAO;
import ftn.com.mojaTeretana.dao.TreningDAO;
import ftn.com.mojaTeretana.model.TipTreninga;
import ftn.com.mojaTeretana.model.Trening;
import ftn.com.mojaTeretana.service.SalaService;

@Repository
public class TipTreningaDAOImpl implements TipTreningaDAO{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private TreningDAO treningDAO;
	
	@Autowired
	private SalaService salaService;
	
	
	private class TipTreningaRowCallbackHandler implements RowCallbackHandler {
		
		private Map<Long, TipTreninga>tipoviTreninga = new LinkedHashMap<>();
		
		@Override
		public void processRow(ResultSet resultSet) throws SQLException {
			int index = 1;
			Long id = resultSet.getLong(index++);
			String ime = resultSet.getString(index++);
			String opis = resultSet.getString(index++);
			Long treningId = resultSet.getLong(index++);
			Trening trening = treningDAO.findOne(treningId);
			
			TipTreninga tipTreninga = tipoviTreninga.get(id);
			if(tipTreninga == null ) {
				tipTreninga = new TipTreninga(id, trening, ime, opis);
				tipoviTreninga.put(tipTreninga.getId(), tipTreninga);
			}
			
		}
		public List<TipTreninga> getTipTreninga() {
			return new ArrayList<>(tipoviTreninga.values());
		}
		
	}
	
	
	
	@Override
	public List<TipTreninga> findAll(Long id) {
		String sql = "Select * from tipTreninga where treningId = ?";
		TipTreningaRowCallbackHandler rowCallbackHandler = new TipTreningaRowCallbackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler, id);
		return rowCallbackHandler.getTipTreninga();
	}

}
