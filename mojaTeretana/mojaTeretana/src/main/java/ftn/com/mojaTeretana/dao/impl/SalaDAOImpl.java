package ftn.com.mojaTeretana.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import ftn.com.mojaTeretana.dao.SalaDAO;
import ftn.com.mojaTeretana.model.Sala;

public class SalaDAOImpl implements SalaDAO {

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private class SalaRowCallbackHandler implements RowCallbackHandler {

		private Map<Long, Sala> sale = new LinkedHashMap<>();
		
		@Override
		public void processRow(ResultSet resultSet) throws SQLException {
			int index = 1;
			Long id = resultSet.getLong(index++);
			Integer kapacitet = resultSet.getInt(index++);
			String oznakaSale = resultSet.getString(index++);
			Sala sala = sale.get(id);
			if(sala == null) {
				sala = new Sala(id, kapacitet, oznakaSale);
				sale.put(sala.getId(), sala);
			}
			
		}
		public List<Sala> getSale() {
			return new ArrayList<>(sale.values());
		}
		
	}
	
	
	@Transactional
	@Override
	public int save(Sala sala) {
		PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				String sql = "Insert into sale (kapacitet, oznakaSale) Values (?, ?)";
				PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				int index = 1;
				preparedStatement.setInt(index++, sala.getKapacitet());
				preparedStatement.setString(index++, sala.getOznakaSale());
				return preparedStatement;
			}
		};
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		boolean uspeh = jdbcTemplate.update(preparedStatementCreator, keyHolder) == 1;
		return uspeh?1:0;
	}

	@Override
	public int delete(Long id) {
		String sql = "Delete from sale where id = ?";
		return jdbcTemplate.update(sql, id);
		
	}

	@Override
	public int update(Sala sala) {
		String sql = "Update sale set kapacitet = ? and oznakaSale = ? where id = ?";
		boolean uspeh = jdbcTemplate.update(sql, sala.getKapacitet(), sala.getOznakaSale(), sala.getId()) == 1;
		return 0;
	}

	@Override
	public List<Sala> findAll() {
		String sql = "Select * from sale";
		SalaRowCallbackHandler rowCallbackHandler = new SalaRowCallbackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler);
		return rowCallbackHandler.getSale();
	}

	@Override
	public Sala findOneById(Long id) {
		String sql = "select * from sale where id = ?";
		SalaRowCallbackHandler rowCallbackHandler = new SalaRowCallbackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler, id);
		return rowCallbackHandler.getSale().get(0);
	}

}
