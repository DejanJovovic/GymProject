package ftn.com.mojaTeretana.dao.impl;

import ftn.com.mojaTeretana.dao.komentarDAO;
import ftn.com.mojaTeretana.model.Komentar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class komentarDAOImpl implements komentarDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private class KomentarRowCallBackHandler implements RowCallbackHandler {

        private Map<Long, Komentar> komentari = new LinkedHashMap<>();

        @Override
        public void processRow(ResultSet resultSet) throws SQLException {
            int index = 1;
            Long id = resultSet.getLong(index++);
            String tekstKomentara = resultSet.getString(index++);
            int ocena = resultSet.getInt(index++);
            String datum = resultSet.getString(index++);
            String autor = resultSet.getString(index++);
            String trening = resultSet.getString(index++);
            String statusKomentara = resultSet.getString(index++);
            boolean anoniman = resultSet.getBoolean(index++);
            Komentar komentar = komentari.get(id);
            if (komentar == null) {
                komentar = new Komentar(id, tekstKomentara, ocena, datum, autor,
                        trening, statusKomentara, anoniman);
                komentari.put(komentar.getId(), komentar);
            }
        }
        public List<Komentar> getKomentari() {
            return new ArrayList<>(komentari.values());
        }
    }

    @Override
    public List<Komentar> FindAllById(Long id) {
        String sql = "SELECT * FROM mojateretana.komentar WHERE idKomentar = ? ";

        komentarDAOImpl.KomentarRowCallBackHandler rowCallbackHandler = new komentarDAOImpl.KomentarRowCallBackHandler();
        jdbcTemplate.query(sql, rowCallbackHandler, id);
        return rowCallbackHandler.getKomentari();
    }

    @Override
    public List<Komentar> FindAll() {
        String sql = "SELECT * FROM mojateretana.komentar";
        komentarDAOImpl.KomentarRowCallBackHandler rowCallbackHandler = new komentarDAOImpl.KomentarRowCallBackHandler();
        jdbcTemplate.query(sql, rowCallbackHandler);
        return rowCallbackHandler.getKomentari();
    }

    @Transactional
    @Override
    public void update(Komentar komentar) {
        String sql = "UPDATE mojaTeretana.komentar SET tekstKomentara = ?, ocena = ?, datum = ?, autor = ?, trening = ?, statusKomentara = ?, anoniman = ? WHERE idKomentar = ?";
        jdbcTemplate.update(sql,komentar.getTekstKomentara(), komentar.getOcena(), komentar.getDatumPostavljanja(),
                komentar.getAutor(),komentar.getTrening(),komentar.getStatus(),komentar.isAnoniman(), komentar.getId());

        return;
    }

    @Override
    public Komentar FindOneById(Long id) {
        String sql = "SELECT * FROM mojateretana.komentar WHERE idKomentar = ? ";

        komentarDAOImpl.KomentarRowCallBackHandler rowCallbackHandler = new komentarDAOImpl.KomentarRowCallBackHandler();
        jdbcTemplate.query(sql, rowCallbackHandler, id);
        return rowCallbackHandler.getKomentari().get(0);
    }
}
