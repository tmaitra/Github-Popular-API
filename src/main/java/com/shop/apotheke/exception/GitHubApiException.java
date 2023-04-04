package com.shop.apotheke.exception;

/**
 * 
 * Exception thrown when an error occurs while fetching data from the GitHub
 * API.
 */
public class GitHubApiException extends RuntimeException {

	private static final long serialVersionUID = -1343505018655186709L;

	/**
	 * 
	 * Constructs a new GitHubApiException with the specified detail message.
	 * 
	 * @param message the detail message.
	 */
	public GitHubApiException(String message) {
		super(message);
	}

	/**
	 * 
	 * Constructs a new GitHubApiException with the specified detail message and
	 * cause.
	 * 
	 * @param message the detail message.
	 * @param cause   the cause.
	 */
	public GitHubApiException(String message, Throwable cause) {
		super(message, cause);
	}
}