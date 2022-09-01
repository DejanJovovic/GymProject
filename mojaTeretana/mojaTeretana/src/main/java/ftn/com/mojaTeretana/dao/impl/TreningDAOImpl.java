package ftn.com.mojaTeretana.dao.impl;

import ftn.com.mojaTeretana.dao.TreningDAO;
import ftn.com.mojaTeretana.model.ENivoTreninga;
import ftn.com.mojaTeretana.model.ETipKorisnika;
import ftn.com.mojaTeretana.model.EVrstaTreninga;
import ftn.com.mojaTeretana.model.TipTreninga;
import ftn.com.mojaTeretana.model.Trening;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TreningDAOImpl implements TreningDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private class TreningRowCallBackHandler implements RowCallbackHandler {

        private Map<Long, Trening> treninzi = new LinkedHashMap<>();

        @Override
        public void processRow(ResultSet resultSet) throws SQLException {
            int index = 1;
            Long id = resultSet.getLong(index++);
            String naziv = resultSet.getString(index++);
            String kratakOpis = resultSet.getString(index++);
            Float cena = resultSet.getFloat(index++);
            String vrsta = resultSet.getString(index++);
            String nivo = resultSet.getString(index++);
            EVrstaTreninga vrstaTreninga = EVrstaTreninga.valueOf(vrsta);
            ENivoTreninga nivoTreninga = ENivoTreninga.valueOf(nivo);
            Integer trajanjeTreninga = resultSet.getInt(index++);
            Integer prosecnaOcena = resultSet.getInt(index++);
            String trener = resultSet.getString(index++);

            Trening trening = treninzi.get(id);
            if (trening == null) {
                trening = new Trening(id, naziv, kratakOpis,
                		cena, vrstaTreninga, nivoTreninga,
                        trajanjeTreninga, prosecnaOcena, trener);
                treninzi.put(trening.getId(), trening); // dodavanje u kolekciju
            }
        }
        public List<Trening> getTreninzi() {
            return new ArrayList<>(treninzi.values());
        }
    }

    @Override
    public List<Trening> findAll() {
        String sql =
                "SELECT * from treninzi";

        TreningRowCallBackHandler rowCallbackHandler = new TreningRowCallBackHandler();
        jdbcTemplate.query(sql, rowCallbackHandler);

        return rowCallbackHandler.getTreninzi();
    }

    @Override
    public Trening findOne(Long treningId) {
        String sql =
                "SELECT * FROM treninzi WHERE id = ? ";

        TreningRowCallBackHandler rowCallbackHandler = new TreningRowCallBackHandler();
        jdbcTemplate.query(sql, rowCallbackHandler, treningId);
        return rowCallbackHandler.getTreninzi().get(0);
    }

    @Transactional
    @Override
    public void update(Trening trening) {
        String sql = "UPDATE mojaTeretana.trening SET naziv = ?, kratakOpis = ?, tipTreninga = ?, cena = ?, vrstaTreninga = ?, nivoTreninga = ?, trajanjeTreninga = ?, prosecnaOcena = ?, trener = ? WHERE idTrening = ?";
        jdbcTemplate.update(sql, trening.getNaziv(), trening.getKratakOpis(), trening.getTipTreninga(), trening.getCena(), trening.getVrstaTreninga().toString(), trening.getNivoTreninga().toString(), trening.getTrajanjeTreninga(), trening.getProsecnaOcena(), trening.getTrener(), trening.getId());

        return;
    }

    @Override
    public int save(Trening trening) {
        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String sql = "INSERT INTO treninzi (naziv, kratakOpis, tipTreninga," +
                        "cena, vrstaTreninga, nivoTreninga, trajanjeTreninga, prosecnaOcena, trener)" +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                int index = 1;
                preparedStatement.setString(index++, trening.getNaziv());
                preparedStatement.setString(index++, trening.getKratakOpis());
                preparedStatement.setFloat(index++, trening.getCena());
                preparedStatement.setString(index++, trening.getVrstaTreninga().toString());
                preparedStatement.setString(index++, trening.getNivoTreninga().toString());
                preparedStatement.setInt(index++, trening.getTrajanjeTreninga());
                preparedStatement.setInt(index++, trening.getProsecnaOcena());
                preparedStatement.setString(index++, trening.getTrener());

                return preparedStatement;
            }
        };
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        boolean uspeh = jdbcTemplate.update(preparedStatementCreator, keyHolder) == 1;
        return uspeh?1:0;
    }
    
    private class CenaHandler implements RowCallbackHandler {
    	private List<Float> list = new ArrayList<Float>();

		@Override
		public void processRow(ResultSet resultSet) throws SQLException {
			int index = 1;
			Float cena = resultSet.getFloat(index++);
			
			list.add(cena);
			
		}
		
		public List<Float> getCene() {
			return new ArrayList<>(list);
		}
    	
    }

	@Override
	public Float sum(Long id) {
		String sql = "select sum(cena) from treninzi tr left join terminTreninga t on t.treningId = tr.id left join Korpa k on k.terminId = t.id where k.korisnikId = ?";
		
		CenaHandler rowCallbackHandler = new CenaHandler();
			jdbcTemplate.query(sql, rowCallbackHandler, id);
			
			return rowCallbackHandler.getCene().get(0);
	}
}
