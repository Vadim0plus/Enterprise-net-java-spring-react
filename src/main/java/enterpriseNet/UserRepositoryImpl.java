package enterpriseNet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

	private JdbcTemplate jdbc;

	private RowMapper customUserMapper = new RowMapper<User>() {

		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getLong(1));
			user.setUserName(rs.getString(2));
			user.setPassword(rs.getString(3));
			user.setFirstName(rs.getString(4));
			user.setLastName(rs.getString(5));
			user.setEmail(rs.getString(6));
			return user;
		}
	};

	@Autowired
	public UserRepositoryImpl(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	public List<User> findAll() {
		return jdbc.query(
				"select id, userName, password, firstName, lastName, email " +
						"from Users order by lastName",
				customUserMapper);
	}

	public User findByID(long userID) {
			String sql = "select id, userName, password, firstName, lastName, email " +
					"from Users where id = ?";
			User user = (User)jdbc.queryForObject(sql,new Object[] {userID}, customUserMapper);
			return user;
	}

	public void save(User user) {
		jdbc.update(
				"insert into Users " +
						"(userName, password, firstName, lastName, email) " +
						"values (?, ?, ?, ?, ?)",
				user.getUserName(),
				user.getPassword(),
				user.getFirstName(),
				user.getLastName(),
				user.getEmail());
	}

}
