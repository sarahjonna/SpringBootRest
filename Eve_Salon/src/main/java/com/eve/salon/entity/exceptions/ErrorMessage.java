package com.eve.salon.entity.exceptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ErrorMessage {

	private Date date;
	private String message;
	private String description;
	private List<String> errors = new ArrayList<>();

	public ErrorMessage() {
	}

	public ErrorMessage(Date date, String message, String description, List<String> errors) {
		super();
		this.date = date;
		this.message = message;
		this.description = description;
		this.errors = errors;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

}