package enterpriseNet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MsgRepositoryImpl implements MsgRepository {

	private JdbcTemplate jdbc;

	private RowMapper customMsgMapper = new RowMapper<Message>() {

		public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
			Message msg = new Message();
			msg.setId(rs.getLong(1));
			msg.setMessage(rs.getString(2));
			msg.setTime(rs.getTime(3));
			return msg;
		}
	};

	@Autowired
	public MsgRepositoryImpl(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	public List<Message> findAll() {
		return jdbc.query(
				"select id, message, saveTime " +
						"from Messages order by saveTime",
				customMsgMapper);
	}

	public Message findByID(long msgID) {
		String sql = "select id, message, saveTime " +
				"from Messages where id = ?";
		Message msg = (Message)jdbc.queryForObject(sql,new Object[] {msgID}, customMsgMapper);
		return msg;
	}

	public void save(Message msg) {
		jdbc.update(
				"insert into Messages " +
						"(message, saveTime) " +
						"values (?, ?)",
				msg.getMessage(),
				msg.getTime());
	}

}
