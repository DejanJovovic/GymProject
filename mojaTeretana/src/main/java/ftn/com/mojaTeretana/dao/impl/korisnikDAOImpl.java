package ftn.com.mojaTeretana.dao.impl;

import ftn.com.mojaTeretana.dao.korisnikDAO;
import ftn.com.mojaTeretana.model.Korisnik;
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
public class korisnikDAOImpl implements korisnikDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private class KorisnikRowCallBackHandler implements RowCallbackHandler {

        private Map<Long, Korisnik> korisnici = new LinkedHashMap<>();

        @Override
        public void processRow(ResultSet resultSet) throws SQLException {
            int index = 1;
            Long id = resultSet.getLong(index++);
            String korisnickoIme = resultSet.getString(index++);
            String lozinka = resultSet.getString(index++);
            String email = resultSet.getString(index++);
            String ime = resultSet.getString(index++);
            String prezime = resultSet.getString(index++);
            String datumRodjenja = resultSet.getString(index++);
            String adresa = resultSet.getString(index++);
            String brojTelefona = resultSet.getString(index++);
            String datumIVremeRegistracije = resultSet.getString(index++);
            String uloga = resultSet.getString(index++);
            Korisnik korisnik = korisnici.get(id);
            if (korisnik == null) {
                korisnik = new Korisnik(id, korisnickoIme, lozinka, email, ime, prezime, datumRodjenja,
                        adresa, brojTelefona, datumIVremeRegistracije, uloga);
                korisnici.put(korisnik.getId(), korisnik); // dodavanje u kolekciju
            }
        }
        public List<Korisnik> getKorisnici() {
            return new ArrayList<>(korisnici.values());
        }
    }

    @Override
    public Korisnik findOneById(Long id) {
        String sql =
                "SELECT idKorisnik, korisnickoIme, lozinka, email, ime, prezime, datumRodjenja, adresa, brojTelefona," +
                        "datumIVremeRegistracije, uloga FROM korisnik " +
                        "WHERE idKorisnik = ? " +
                        "ORDER BY idKorisnik";
        KorisnikRowCallBackHandler rowCallbackHandler = new KorisnikRowCallBackHandler();
        jdbcTemplate.query(sql, rowCallbackHandler, id);
        return rowCallbackHandler.getKorisnici().get(0);
    }

    @Override
    public Korisnik findOneByUsername(String korisnickoIme) {
        String sql =
                "SELECT idKorisnik, korisnickoIme, lozinka, email, ime, prezime, datumRodjenja, adresa, brojTelefona," +
                        "datumIVremeRegistracije, uloga FROM korisnik " +
                        "WHERE korisnickoIme = ? " +
                        "ORDER BY idKorisnik";
        KorisnikRowCallBackHandler rowCallbackHandler = new KorisnikRowCallBackHandler();
        jdbcTemplate.query(sql, rowCallbackHandler, korisnickoIme);
        return rowCallbackHandler.getKorisnici().get(0);
    }

    @Override
    public Korisnik findOne(String korisnickoIme, String lozinka) {
        String sql =
                "SELECT idKorisnik, korisnickoIme, lozinka, email, ime, prezime, datumRodjenja, adresa, brojTelefona," +
                        "datumIVremeRegistracije, uloga FROM korisnik " +
                        "WHERE korisnickoIme = ? AND lozinka = ?" +
                        "ORDER BY idKorisnik";
        KorisnikRowCallBackHandler rowCallbackHandler = new KorisnikRowCallBackHandler();
        jdbcTemplate.query(sql, rowCallbackHandler, korisnickoIme, lozinka);
        if(rowCallbackHandler.getKorisnici().size() == 0) {
            return null;
        }
        return rowCallbackHandler.getKorisnici().get(0);
    }

    @Override
    public List<Korisnik> findAll() {
        String sql =
                "SELECT idKorisnik, korisnickoIme, lozinka, email, ime, prezime, datumRodjenja, adresa, brojTelefona," +
                        "datumIVremeRegistracije, uloga FROM korisnik " +
                        "ORDER BY idKorisnik";
        KorisnikRowCallBackHandler rowCallbackHandler = new KorisnikRowCallBackHandler();
        jdbcTemplate.query(sql, rowCallbackHandler);
        return rowCallbackHandler.getKorisnici();
    }

    @Transactional
    @Override
    public int save(Korisnik korisnik) {
        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String sql = "INSERT INTO korisnik (korisnickoIme, lozinka, email, ime," +
                        "prezime, datumRodjenja, adresa, brojTelefona, datumIVremeRegistracije,uloga)" +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                int index = 1;
                preparedStatement.setString(index++, korisnik.getKorisnickoIme());
                preparedStatement.setString(index++, korisnik.getLozinka());
                preparedStatement.setString(index++, korisnik.getEmail());
                preparedStatement.setString(index++, korisnik.getIme());
                preparedStatement.setString(index++, korisnik.getPrezime());
                preparedStatement.setString(index++, korisnik.getDatumRodjenja());
                preparedStatement.setString(index++, korisnik.getAdresa());
                preparedStatement.setString(index++, korisnik.getBrojTelefona());
                preparedStatement.setString(index++, korisnik.getDatumIVremeRegistracije());
                preparedStatement.setString(index++, korisnik.getUloga());
                return preparedStatement;
            }
        };
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        boolean uspeh = jdbcTemplate.update(preparedStatementCreator, keyHolder) == 1;
        return uspeh?1:0;
    }
}
