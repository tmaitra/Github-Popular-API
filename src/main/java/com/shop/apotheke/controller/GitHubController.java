package com.shop.apotheke.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shop.apotheke.model.GitHubRepo;
import com.shop.apotheke.services.GitHubService;

@RestController
@RequestMapping("/api")
public class GitHubController {

	// inject the GitHubService dependency
	private final GitHubService gitHubService;

	public GitHubController(GitHubService gitHubService) {
		this.gitHubService = gitHubService;
	}

	/**
	 * GET endpoint to retrieve popular GitHub repositories date parameter is
	 * required and must be in ISO date format (yyyy-MM-dd) language parameter is
	 * optional and limits results to the given programming language limit parameter
	 * is optional and defaults to 10, maximum value is 100
	 * 
	 */
	@GetMapping("/popular-repos")
	public List<GitHubRepo> getPopularRepositories(
			@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
			@RequestParam(value = "language", required = false) String language,
			@RequestParam(value = "limit", defaultValue = "10") int limit) {

		return gitHubService.getPopularRepositories(date, language, limit);
	}

}
