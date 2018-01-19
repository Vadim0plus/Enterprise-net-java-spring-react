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

import javax.validation.Valid;

@RestController
@RequestMapping("/msg")
public class MsgController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private ObjectMapper mapper = new ObjectMapper();
	private MsgRepository msgRepo;

	@Autowired
	public MsgController(MsgRepository msgRepo) {
		this.msgRepo = msgRepo;
	}

	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String saveMessage(@Valid @RequestBody Message msg) {
		try {
			log.info("New message:" +
					mapper.writeValueAsString(msg));
		} catch (JsonProcessingException e) {log.error(e.toString());}
		msgRepo.save(msg);
		return "Successful Processing";
	}

	@RequestMapping(value="/{msgID}", method = RequestMethod.GET)
	public Message showMessage(@PathVariable long msgID) {
		Message msg = msgRepo.findByID(msgID);
		return msg;
	}

}
