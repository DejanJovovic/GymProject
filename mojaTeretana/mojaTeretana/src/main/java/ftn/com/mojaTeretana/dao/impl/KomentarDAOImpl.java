package ftn.com.mojaTeretana.dao.impl;

import ftn.com.mojaTeretana.dao.KomentarDAO;
import ftn.com.mojaTeretana.dao.KorisnikDAO;
import ftn.com.mojaTeretana.dao.TreningDAO;
import ftn.com.mojaTeretana.model.Status;
import ftn.com.mojaTeretana.model.Komentar;
import ftn.com.mojaTeretana.model.Korisnik;
import ftn.com.mojaTeretana.model.Trening;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class KomentarDAOImpl implements KomentarDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TreningDAO treningDAO;

    private class KomentarRowCallBackHandler implements RowCallbackHandler {

        private Map<Long, Komentar> komentari = new LinkedHashMap<>();

        @Override
        public void processRow(ResultSet resultSet) throws SQLException {
            int index = 1;
            Long id = resultSet.getLong(index++);
            String tekstKomentara = resultSet.getString(index++);
            Integer ocena = resultSet.getInt(index++);
            LocalDate datum = resultSet.getTimestamp(index++).toLocalDateTime().toLocalDate();
            Long idtre = resultSet.getLong(index++);
            Trening trening = treningDAO.findOne(idtre);
            String statusKomentara =resultSet.getString(index++);
            String autor = resultSet.getString(index++);
            Status status = Status.valueOf(statusKomentara);
          

            boolean anoniman = resultSet.getBoolean(index++);
            
            Komentar komentar = komentari.get(id);
            if (komentar == null) {
                komentar = new Komentar(id, tekstKomentara, ocena, datum, 
                        trening, status, autor, anoniman);
                komentari.put(komentar.getId(), komentar);
            }
        }
        public List<Komentar> getKomentari() {
            return new ArrayList<>(komentari.values());
        }
    }

    @Override
    public List<Komentar> findAllById(Long id) {
        String sql = "SELECT * from komentari WHERE trening = ? and statusKomentara = 'CEKANJE' ";

        KomentarRowCallBackHandler rowCallbackHandler = new KomentarRowCallBackHandler();
        jdbcTemplate.query(sql, rowCallbackHandler, id);
        return rowCallbackHandler.getKomentari();
    }

    @Override
    public List<Komentar> findAll() {
        String sql = "SELECT * FROM komentari ";
        KomentarRowCallBackHandler rowCallbackHandler = new KomentarRowCallBackHandler();
        jdbcTemplate.query(sql, rowCallbackHandler);
        return rowCallbackHandler.getKomentari();
    }


    @Override
    public Komentar findOne(Long id) {
        String sql = "SELECT * FROM komentari WHERE id = ? ";

        KomentarRowCallBackHandler rowCallbackHandler = new KomentarRowCallBackHandler();
        jdbcTemplate.query(sql, rowCallbackHandler, id);
        return rowCallbackHandler.getKomentari().get(0);
    }


	@Override
	public int delete(Long id) {
			
		String sql = "Update komentari set statusKomentara = 'NEODOBREN' where id = ?";
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public int odobreno(Long id) {
		String sql = "Update komentari set statusKomentara = 'ODOBREN' where id = ?";
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public int save(Komentar komentar) {
		String sql = "Insert into komentari (tekstKomentara, ocena, datum, statusKomentara, trening, autor, anoniman) values(?, ?, ?, ?, ?, ?, ?)";
		return jdbcTemplate.update(sql, komentar.getTekstKomentara(),
				komentar.getOcena(), komentar.getDatum(), komentar.getStatus().toString(), 
				 komentar.getTrening().getId(), komentar.getAutor(), komentar.isAnoniman());
		
	}
}
