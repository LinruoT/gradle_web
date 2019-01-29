package bitter.data;

import bitter.Bittle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcBittleRepository implements BittleRepository{
    private JdbcOperations jdbc;

    @Autowired //构造器，jdbc Beam在DataConfig类jdbcTemplate(DataSource dataSource)
    public JdbcBittleRepository(JdbcOperations jdbc) {
        this.jdbc=jdbc;
    }


    @Override//query方法需要sql，rowMapper，?的参数
    public List<Bittle> findBittles(Long max, int count) {
        return jdbc.query(
                "select id, message, created_at, latitude, longitude" +
                        " from Bittle" +
                        " where id < ?" +
                        " order by created_at desc limit 20",
                new BittleRowMapper(), max);
    }

    @Override
    public Bittle findOne(Long bittleId) {
        return jdbc.queryForObject(
                "select id, message, created_at, latitude, longitude" +
                        " from Bittle" +
                        " where id = ?",
                new BittleRowMapper(), bittleId);
    }

    @Override
    public void save(Bittle bittle) {
        jdbc.update(
                "insert into Bittle (message, created_at, latitude, longitude)" +
                        " values (?, ?, ?, ?)",
                bittle.getMessage(),
                bittle.getTime(),
                bittle.getLatitude(),
                bittle.getLongitude());
    }


    //BittleRowMapper实现RowMapper，实现mapRow方法
    private static class BittleRowMapper implements RowMapper<Bittle> {
        public Bittle mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Bittle(
                    rs.getLong("id"),
                    rs.getString("message"),
                    rs.getDate("created_at"),
                    rs.getDouble("longitude"),
                    rs.getDouble("latitude"));
        }
    }
}
