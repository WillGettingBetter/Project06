package com.eec.entity;

import java.util.Date;

/**
 * ÁôÑÔ×Ö¶Î
 * 
 * @author SJF18
 * 
 */
public class FounderMessage {
	private int messageId;
	private int userId;
	private String userName;
	private int fileId;
	private String messageContent;
	private Date messageDate;

	public FounderMessage() {
		super();
	}

	public FounderMessage(int messageId, int userId, String userName,
			int fileId, String messageContent, Date messageDate) {
		super();
		this.messageId = messageId;
		this.userId = userId;
		this.userName = userName;
		this.fileId = fileId;
		this.messageContent = messageContent;
		this.messageDate = messageDate;
	}

	@Override
	public String toString() {
		return "FounderMessage [messageId=" + messageId + ", userId=" + userId
				+ ", userName=" + userName + ", fileId=" + fileId
				+ ", messageContent=" + messageContent + ", messageDate="
				+ messageDate + "]";
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public Date getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}

}
