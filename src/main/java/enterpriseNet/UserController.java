package enterpriseNet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private ObjectMapper mapper = new ObjectMapper();
	private UserRepository userRepo;

	@Autowired
	public UserController(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String processRegistration(@RequestBody User user) {
		try {
			log.info("New user:" +
					mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user));
		} catch (JsonProcessingException e) {log.error(e.toString());}
		userRepo.save(user);
		return "Successful Registration";
	}

	@RequestMapping(value="/{userID}", method = RequestMethod.GET)
	public User showUserProfile(@PathVariable long userID) {
		User user = userRepo.findByID(userID);
		return user;
	}

}
