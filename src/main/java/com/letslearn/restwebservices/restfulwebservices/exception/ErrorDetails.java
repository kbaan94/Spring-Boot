package com.letslearn.restwebservices.restfulwebservices.exception;

import java.time.LocalDateTime;

/* Whenever an error happens, we want to 
 * return this structure to describe the error */

public class ErrorDetails {
	// timestamp for when error happens
	private LocalDateTime timestamp;
	// error message
	private String message;
	// error details
	private String details;

	public ErrorDetails(LocalDateTime timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

}
