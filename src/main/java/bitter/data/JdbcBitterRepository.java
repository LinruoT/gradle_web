package bitter.data;

import bitter.domain.Bitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//@Repository
public class JdbcBitterRepository implements BitterRepository{
    private JdbcOperations jdbc;

    @Autowired //构造器，jdbc Beam自动装配DataConfig类jdbcTemplate(DataSource dataSource)
    public JdbcBitterRepository(JdbcOperations jdbc) {
        this.jdbc=jdbc;
    }

    @Override
    public Bitter save(Bitter bitter) {
        try {
            jdbc.update("insert into Bitter (username, password, first_name, last_name, email)" +
                            " values (?, ?, ?, ?, ?)",
                    bitter.getUsername(),
                    bitter.getPassword(),
                    bitter.getFirstName(),
                    bitter.getLastName(),
                    bitter.getEmail());
            return bitter;
        } catch (Exception e) {
            return null;
        }
    }

    @Override //返回username对应的bitter类
    public Bitter findByUsername(String username) {
        return jdbc.queryForObject(
                "select id, username, null, first_name, last_name, email from Bitter where username=?",
                new BitterRowMapper(),
                username);
    }
    private static class BitterRowMapper implements RowMapper<Bitter> {
        public Bitter mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Bitter(
                    rs.getLong("id"),
                    rs.getString("username"),
                    null,
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email"));
        }
    }
}
