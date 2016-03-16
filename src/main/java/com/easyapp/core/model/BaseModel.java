package com.easyapp.core.model;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

abstract public class BaseModel {
	private enum MessageType {
		SUCCESS, WARNING, ERROR
	};

	private int messageCode;
	private MessageType messageType;
	private String messageText;
	private int rowsAffected;

	public BaseModel() {
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

	public int getRowsAffected() {
		return rowsAffected;
	}

	protected void setRowsAffected(final int rowsAffected) {
		this.rowsAffected = rowsAffected;
	}

	public void success(String successMessage) {
		setMessageCode(0);
		setMessageType(MessageType.SUCCESS);
		setMessageText(successMessage);
	}

	public void warning(int warningCode, String warningMessage) {
		setMessageCode(warningCode);
		setMessageType(MessageType.WARNING);
		setMessageText(warningMessage);
	}

	public void error(int errorCode, String errorMessage) {
		setMessageCode(errorCode);
		setMessageType(MessageType.ERROR);
		setMessageText(errorMessage);
	}

	public void clear() {
		success("SUCCESS");
	}

	public String toJSON() {
		String json = null;
		ObjectMapper jsonMapper = new ObjectMapper();

		try {
			json = jsonMapper.writeValueAsString(this);
		} catch (JsonProcessingException jpe) {
			jpe.printStackTrace();
		}

		return json;
	}

	public static BaseModel fromJSON(String json, Class<? extends BaseModel> modelClass) {
		BaseModel model = null;
		ObjectMapper jsonMapper = new ObjectMapper();

		try {
			model = jsonMapper.readValue(json, modelClass);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return model;
	}
}