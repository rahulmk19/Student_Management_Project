package com.student.Exception;

import java.net.http.HttpHeaders;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(StudentManagementException.class)
	public ResponseEntity<MyErrorDetails> StudentExceptionHandler(StudentManagementException stu, WebRequest req) {

		MyErrorDetails err = new MyErrorDetails(stu.getMessage(), req.getDescription(false), LocalDateTime.now());

		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException me) {
		MyErrorDetails err = new MyErrorDetails(me.getBindingResult().getFieldError().getDefaultMessage(),
				"validation error", LocalDateTime.now());
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message", "Endpoint not found");

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> otherExceptionHandler(Exception e, WebRequest req) {
		MyErrorDetails error = new MyErrorDetails(e.getMessage(), req.getDescription(false), LocalDateTime.now());

		return new ResponseEntity<MyErrorDetails>(error, HttpStatus.ACCEPTED);
	}
}
