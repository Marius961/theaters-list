package ua.theater.db.dao.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ua.theater.db.dao.interfaces.PlayDAO;
import ua.theater.db.models.Play;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PlayDAOImpl implements PlayDAO {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Play getPlayById(int playId) {
        String sql = "SELECT * FROM play WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", playId);
        try {
            return jdbcTemplate.queryForObject(sql, params, new PlayMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Play> getAllPlaysByTheaterID(int theaterId) {
        String sql = "SELECT * FROM play WHERE theater_id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", theaterId);
        try {
            return jdbcTemplate.query(sql, params, new PlayMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Play> getAllPlays() {
        String sql = "SELECT * FROM play";
        try {
            return jdbcTemplate.query(sql, new PlayMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int insertPlay(Play play) {
        return insertPlay(play.getName(), play.getProductionDirector(), play.getDescription(), play.getTheaterId(), play.getDate());
    }

    private int insertPlay(String name, String prodDirector, String description, int theaterId, String date) {
        String sql = "INSERT INTO play (theater_id, name, date, prod_director, description) VALUES (:theaterId, :play_name, :play_date, :prodDir, :description)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("theaterId", theaterId);
        params.addValue("play_name", name);
        params.addValue("play_date", date);
        params.addValue("prodDir", prodDirector);
        params.addValue("description", description);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(sql, params, keyHolder);
            return Integer.parseInt(keyHolder.getKey().toString());
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public void deletePlayById(int playId) {
        String sql = "DELETE FROM play WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", playId);
        jdbcTemplate.update(sql, params);
    }

    @Override
    public void updatePlay(Play play) {
        String sql = "UPDATE play SET theater_id=:theaterId, name=:name, date=:date, prod_director=:prodDirector, description=:description WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", play.getId());
        params.addValue("theaterId", play.getTheaterId());
        params.addValue("name", play.getName());
        params.addValue("date", play.getDate());
        params.addValue("prodDirector", play.getProductionDirector());
        params.addValue("description", play.getDescription());
        jdbcTemplate.update(sql, params);
    }

    @Override
    public Play getPlay(Play play) {
        String sql = "SELECT * FROM play WHERE theater_id=:theaterId AND name=:name AND date=:play_date AND prod_director=:prodDirector AND description=:description";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("theaterId", play.getTheaterId());
        params.addValue("name", play.getName());
        params.addValue("play_date", play.getDate());
        params.addValue("prodDirector", play.getProductionDirector());
        params.addValue("description", play.getDescription());
        try {
            return jdbcTemplate.queryForObject(sql, params, new PlayMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Play> searchPlaysByName(String name) {
        String sql = "SELECT * FROM play WHERE name LIKE :upName or name LIKE :lowName ";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("upName", "%" + name.toUpperCase() + "%");
        params.addValue("lowName", "%" + name.toLowerCase() + "%");
        try {
            return jdbcTemplate.query(sql, params, new PlayMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void deleteAllActorsForPlay(int playId) {
        String sql = "DELETE FROM play_map WHERE play_id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", playId);
        jdbcTemplate.update(sql, params);
    }

    @Override
    public void deleteAllPlayForTheater(int id) {
        String sql = "DELETE FROM play WHERE theater_id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        jdbcTemplate.update(sql, params);
    }

    @Override
    public int getPlaysCount(int theaterId) {
        String sql = "SELECT count(*) FROM play WHERE theater_id=:theaterId";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("theaterId", theaterId);
        return jdbcTemplate.queryForObject(sql,params, Integer.class);
    }

    private static final class PlayMapper implements RowMapper<Play> {
        @Override
        public Play mapRow(ResultSet rs, int i) throws SQLException {
            Play play = new Play();
            play.setDate(rs.getString("date"));
            play.setDescription(rs.getString("description"));
            play.setName(rs.getString("name"));
            play.setProductionDirector(rs.getString("prod_director"));
            play.setId(rs.getInt("id"));
            play.setTheaterId(rs.getInt("theater_id"));
            return play;
        }
    }
}
