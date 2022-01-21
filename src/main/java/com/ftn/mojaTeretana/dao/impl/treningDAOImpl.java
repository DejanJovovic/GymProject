package com.ftn.mojaTeretana.dao.impl;

import com.ftn.mojaTeretana.dao.TreningDAO;
import com.ftn.mojaTeretana.model.Korisnik;
import com.ftn.mojaTeretana.model.TipTreninga;
import com.ftn.mojaTeretana.model.Trening;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class treningDAOImpl implements TreningDAO {

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
            String slika = resultSet.getString(index++);
            String tipTreninga1 = resultSet.getString(index++);

            TipTreninga tipTreninga = new TipTreninga();
            tipTreninga.setIme(tipTreninga1);

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

    @Override
    public Trening findOneByCena(int cena) {
        return null;
    }
}
