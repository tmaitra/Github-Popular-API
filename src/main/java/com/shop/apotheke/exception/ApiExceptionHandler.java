package com.shop.apotheke.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * 
 * Global exception handler for the API.
 */
@ControllerAdvice
public class ApiExceptionHandler {

	/**
	 * 
	 * Handle exceptions thrown when making a call to the GitHub API.
	 * 
	 * @param ex The GitHubApiException that was thrown.
	 * 
	 * @return A ResponseEntity with an error message and a 500 Internal Server
	 *         Error status code.
	 */
	@ExceptionHandler(GitHubApiException.class)
	public ResponseEntity<Object> handleGitHubApiException(GitHubApiException ex) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());

		return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * 
	 * Handle exceptions thrown when a method argument is of the incorrect type.
	 * 
	 * @param ex The MethodArgumentTypeMismatchException that was thrown.
	 * 
	 * @return A ResponseEntity with an error message and a 400 Bad Request status
	 *         code.
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", "Invalid parameter: " + ex.getName());

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

}