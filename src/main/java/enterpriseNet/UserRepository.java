package enterpriseNet;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

	public List<User> findAll();

	public User findByID(long userID);

	public void save(User user);

}
