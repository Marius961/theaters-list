package ua.theater.db.dao.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ua.theater.db.dao.interfaces.TheaterDAO;
import ua.theater.db.models.Theater;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class TheaterDAOImpl implements TheaterDAO {


    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    @Override
    public List<Theater> getTheaters() {
        String sql = "SELECT * FROM theaters";
        try {
            return jdbcTemplate.query(sql, new TheaterMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Theater getTheaterById(int theaterId) {
        String sql = "SELECT * FROM theaters WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", theaterId);

        try {
            return jdbcTemplate.queryForObject(sql, params, new TheaterMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Theater getTheater(Theater theater) {
        String sql = "SELECT * FROM theaters WHERE name=:name and tel_number=:telNum and address=:addr";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("telNum", theater.getTel());
        params.addValue("name", theater.getName());
        params.addValue("addr", theater.getAddress());
        try {
            return jdbcTemplate.queryForObject(sql, params, new TheaterMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int insertTheater(Theater theater) {
        return insertTheater(theater.getName(), theater.getAddress(), theater.getTel());
    }

    public int insertTheater(String name, String address, String tel) {
        String sql = "INSERT INTO theaters (name, address, tel_number) VALUES (:name,:address,:tel)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", name);
        params.addValue("address", address);
        params.addValue("tel", tel);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            jdbcTemplate.update(sql, params, keyHolder);
            return Integer.parseInt(keyHolder.getKey().toString());
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public void deleteTheaterById(int theaterId) {
        String sql = "DELETE FROM theaters WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", theaterId);
        jdbcTemplate.update(sql, params);
    }

    @Override
    public void updateTheater(Theater theater) {
        String sql = "UPDATE theaters SET name=:name, tel_number=:telNum, address=:addr WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("telNum", theater.getTel());
        params.addValue("name", theater.getName());
        params.addValue("addr", theater.getAddress());
        params.addValue("id", theater.getId());
        jdbcTemplate.update(sql, params);
    }


    private static final class TheaterMapper implements RowMapper<Theater> {
        public Theater mapRow(ResultSet rs, int rowNum) throws SQLException {
            Theater theater = new Theater();
            theater.setId(rs.getInt("id"));
            theater.setAddress(rs.getString("address"));
            theater.setName(rs.getString("name"));
            theater.setTel(rs.getString("tel_number"));
            return theater;
        }
    }
}
