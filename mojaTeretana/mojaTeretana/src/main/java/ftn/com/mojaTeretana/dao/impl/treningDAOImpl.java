package ftn.com.mojaTeretana.dao.impl;

import ftn.com.mojaTeretana.dao.TreningDAO;
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
public class treningDAOImpl implements TreningDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    class TreningRowCallBackHandler implements RowCallbackHandler {

        private Map<Long, Trening> treninzi = new LinkedHashMap<>();

        @Override
        public void processRow(ResultSet resultSet) throws SQLException {
            int index = 1;
            Long id = resultSet.getLong(index++);
            String naziv = resultSet.getString(index++);

            String kratakOpis = resultSet.getString(index++);
            String slika = resultSet.getString(index++);
            String tipTreninga = resultSet.getString(index++);

            int cena = resultSet.getInt(index++);
            String vrstaTreninga = resultSet.getString(index++);
            String nivoTreninga = resultSet.getString(index++);
            int trajanjeTreninga = resultSet.getInt(index++);
            int prosecnaOcena = resultSet.getInt(index++);
            String trener = resultSet.getString(index++);

            Trening trening = treninzi.get(id);
            if (trening == null) {
                trening = new Trening(id, naziv, kratakOpis, slika, tipTreninga, cena, vrstaTreninga,
                        nivoTreninga, trajanjeTreninga, prosecnaOcena, trener);
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
                "SELECT idTrening, naziv, kratakOpis, slika, tipTreninga, cena, vrstaTreninga, nivoTreninga," +
                        "trajanjeTreninga, prosecnaOcena, trener FROM trening";

        TreningRowCallBackHandler rowCallbackHandler = new TreningRowCallBackHandler();
        jdbcTemplate.query(sql, rowCallbackHandler);

        return rowCallbackHandler.getTreninzi();
    }

    @Override
    public Trening findOneById(Long id) {
        String sql =
                "SELECT * FROM mojaTeretana.trening WHERE idTrening = ? ";

        TreningRowCallBackHandler rowCallbackHandler = new TreningRowCallBackHandler();
        jdbcTemplate.query(sql, rowCallbackHandler, id);
        return rowCallbackHandler.getTreninzi().get(0);
    }

    @Transactional
    @Override
    public void update(Trening trening) {
        String sql = "UPDATE mojaTeretana.trening SET naziv = ?, kratakOpis = ?, slika = ?, tipTreninga = ?, cena = ?, vrstaTreninga = ?, nivoTreninga = ?, trajanjeTreninga = ?, prosecnaOcena = ?, trener = ? WHERE idTrening = ?";
        jdbcTemplate.update(sql, trening.getNaziv(), trening.getKratakOpis(), trening.getSlika(), trening.getTipTreninga(), trening.getCena(), trening.getVrstaTreninga(), trening.getNivoTreninga(), trening.getTrajanjeTreninga(), trening.getProsecnaOcena(), trening.getTrener(), trening.getId());

        return;
    }

    @Override
    public int save(Trening trening) {
        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String sql = "INSERT INTO trening (naziv, kratakOpis, slika, tipTreninga," +
                        "cena, vrstaTreninga, nivoTreninga, trajanjeTreninga, prosecnaOcena, trener)" +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                int index = 1;
                preparedStatement.setString(index++, trening.getNaziv());
                preparedStatement.setString(index++, trening.getKratakOpis());
                preparedStatement.setString(index++, trening.getSlika());
                preparedStatement.setString(index++, trening.getTipTreninga());
                preparedStatement.setInt(index++, trening.getCena());
                preparedStatement.setString(index++, trening.getVrstaTreninga());
                preparedStatement.setString(index++, trening.getNivoTreninga());
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
}
