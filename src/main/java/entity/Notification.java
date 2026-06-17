package entity;

import java.time.LocalDate;

public class Notification {
	private String userId;
	private LocalDate sendDate;
	private String messageType;

	public Notification() {

	}

	public String getUserId() {
	    return userId;
	}

	public void setUserId(String userId) {
	    this.userId = userId;
	}

	public LocalDate getSendDate() {
	    return sendDate;
	}

	public void setSendDate(LocalDate sendDate) {
	    this.sendDate = sendDate;
	}

	public String getMessageType() {
	    return messageType;
	}

	public void setMessageType(String messageType) {
	    this.messageType = messageType;
	}
}
