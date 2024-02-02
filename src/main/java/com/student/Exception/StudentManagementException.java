package com.student.Exception;

public class StudentManagementException extends RuntimeException {

	public StudentManagementException() {
		super();
	}

	public StudentManagementException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public StudentManagementException(String message, Throwable cause) {
		super(message, cause);
	}

	public StudentManagementException(String message) {
		super(message);
	}

	public StudentManagementException(Throwable cause) {
		super(cause);
	}

}
