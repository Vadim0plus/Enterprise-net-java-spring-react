package enterpriseNet;

import java.util.Date;

public class Message {

	private long id;
	private String message;
	private Date time;

	public Message() {}

	public Message(String message, Date time) {
		this(0,message,time);
	}

	public Message(long id, String message, Date time) {
		this.id = id;
		this.message = message;
		this.time = time;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}


}
