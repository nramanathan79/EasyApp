package com.easyapp.core.data;

import java.io.IOException;

import com.easyapp.core.event.Event;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

abstract public class BaseData implements Event {
	protected static final ObjectMapper jsonMapper = new ObjectMapper();

	private enum MessageType {
		SUCCESS, WARNING, ERROR, INFO
	};

	private int messageCode;
	private MessageType messageType;
	private String messageText;

	public BaseData() {
		success("SUCCESS");
	}

	public int getMessageCode() {
		return messageCode;
	}

	protected void setMessageCode(final int messageCode) {
		this.messageCode = messageCode;
	}

	public MessageType getMessageType() {
		return messageType;
	}

	protected void setMessageType(final MessageType messageType) {
		this.messageType = messageType;
	}

	public String getMessageText() {
		return messageText;
	}

	protected void setMessageText(final String messageText) {
		this.messageText = messageText;
	}

	public void success(final String successMessage) {
		setMessageCode(0);
		setMessageType(MessageType.SUCCESS);
		setMessageText(successMessage);
	}

	public void warning(final int warningCode, final String warningMessage) {
		setMessageCode(warningCode);
		setMessageType(MessageType.WARNING);
		setMessageText(warningMessage);
	}

	public void error(final int errorCode, final String errorMessage) {
		setMessageCode(errorCode);
		setMessageType(MessageType.ERROR);
		setMessageText(errorMessage);
	}

	public void info(final String infoMessage) {
		setMessageCode(0);
		setMessageType(MessageType.INFO);
		setMessageText(infoMessage);
	}

	public void clear() {
		success("SUCCESS");
	}

	public String toJson() {
		String json = null;
		ObjectMapper jsonMapper = new ObjectMapper();

		try {
			json = jsonMapper.writeValueAsString(this);
		} catch (JsonProcessingException jpe) {
			jpe.printStackTrace();
		}

		return json;
	}

	public static BaseData fromJson(final String json, Class<? extends BaseData> modelClass) {
		BaseData model = null;

		try {
			model = jsonMapper.readValue(json, modelClass);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return model;
	}
}