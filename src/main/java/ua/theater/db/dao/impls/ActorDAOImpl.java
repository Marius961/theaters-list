package ua.theater.db.dao.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ua.theater.db.dao.interfaces.ActorDAO;
import ua.theater.db.models.Actor;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class ActorDAOImpl implements ActorDAO {


    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Actor getActorById(int id) {
        String sql = "SELECT * FROM actor WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        try {
            return jdbcTemplate.queryForObject(sql, params, new ActorMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Actor getActorByName(String name) {
        String sql = "SELECT * FROM actor WHERE name=:name";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", name);
        try {
            return jdbcTemplate.queryForObject(sql, params, new ActorMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Actor> getActorsForPlay(int playId) {
        String sql = "SELECT * FROM actor at, play_map pm WHERE at.id=pm.actor_id AND pm.play_id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", playId);
        try {
            return jdbcTemplate.query(sql, params, new ActorMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Actor> getAllActors() {
        String sql = "SELECT * FROM actor";
        try {
            return jdbcTemplate.query(sql, new ActorMapper());
        } catch (Exception e) {
            return null;
        }
    }


    @Override
    public void insertActorToPlay(int actorId, int playId) {
        String sql = "INSERT INTO play_map (play_id, actor_iD) VALUES (:playId, :actorId)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("playId", playId);
        params.addValue("actorId", actorId);
        jdbcTemplate.update(sql, params);
    }

    @Override
    public void updateActor(Actor actor) {
        String sql = "UPDATE actor SET name=:name WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", actor.getName());
        params.addValue("id", actor.getId());
        jdbcTemplate.update(sql, params);
    }

    public int insertActor(String name) {
        String sql = "INSERT INTO actor (name) VALUES (:name)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", name);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            jdbcTemplate.update(sql, params, keyHolder);
            return Integer.parseInt(keyHolder.getKey().toString());
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public void deleteActor(int actorId) {
        String sql = "DELETE FROM actor WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", actorId);
        jdbcTemplate.update(sql, params);
    }

    @Override
    public void deleteActorFromPlay(int actorId, int playId) {
        String sql = "DELETE FROM play_map WHERE actor_id=:actorId AND play_id=:playId";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("actorId", actorId);
        params.addValue("playId", playId);
        jdbcTemplate.update(sql, params);
    }

    private static final class ActorMapper implements RowMapper<Actor> {

        @Override
        public Actor mapRow(ResultSet rs, int i) throws SQLException {
            Actor actor = new Actor();
            actor.setId(rs.getInt("id"));
            actor.setName(rs.getString("name"));
            return actor;
        }
    }
}
