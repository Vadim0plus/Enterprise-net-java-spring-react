package enterpriseNet;

import java.util.List;

public interface MsgRepository {

	public List<Message> findAll();

	public Message findByID(long msgID);

	public void save(Message msg);
}
