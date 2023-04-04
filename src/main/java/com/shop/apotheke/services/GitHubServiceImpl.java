package com.shop.apotheke.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.shop.apotheke.exception.GitHubApiException;
import com.shop.apotheke.model.GitHubRepo;
import com.shop.apotheke.model.GitHubRepoSearchResponse;

/**
 * 
 * Implementation of the {@link GitHubService} interface that retrieves popular
 * GitHub repositories from the GitHub API.
 */
@Service
public class GitHubServiceImpl implements GitHubService {

	private final RestTemplate restTemplate;
	private final String gitHubApiUrl;

	/**
	 * 
	 * Constructor to create a new instance of {@link GitHubServiceImpl}.
	 * 
	 * @param restTemplate The {@link RestTemplate} to be used for making HTTP
	 *                     requests.
	 * @param gitHubApiUrl The base URL of the GitHub API.
	 */
	public GitHubServiceImpl(RestTemplate restTemplate, @Value("${github.api.url}") String gitHubApiUrl) {
		this.restTemplate = restTemplate;
		this.gitHubApiUrl = gitHubApiUrl;
	}

	/**
	 * 
	 * Retrieves popular GitHub repositories from the GitHub API based on the given
	 * date, language, and limit parameters.
	 * 
	 * @param date     The date from which to retrieve popular repositories.
	 * 
	 * @param language The language to filter popular repositories by (optional).
	 * 
	 * @param limit    The maximum number of popular repositories to retrieve
	 *                 (default 10).
	 * 
	 * @return A list of {@link GitHubRepo} objects representing the popular
	 *         repositories.
	 * 
	 * @throws GitHubApiException If an error occurs while fetching data from the
	 *                            GitHub API.
	 */
	public List<GitHubRepo> getPopularRepositories(LocalDate date, String language, int limit) {
		String url = buildSearchUrl(date, language, limit);
		ResponseEntity<GitHubRepoSearchResponse> response = restTemplate.getForEntity(url,
				GitHubRepoSearchResponse.class);

		if (!response.getStatusCode().is2xxSuccessful()) {
			throw new GitHubApiException("Error occurred while fetching data from GitHub API");
		}

		List<GitHubRepo> popularRepos = response.getBody().getItems();
		return popularRepos.stream().limit(limit).collect(Collectors.toList());
	}

	/**
	 * 
	 * Builds the search URL for retrieving popular GitHub repositories from the
	 * GitHub API based on the given date,
	 * 
	 * language, and limit parameters.
	 * 
	 * @param date     The date from which to retrieve popular repositories.
	 * 
	 * @param language The language to filter popular repositories by (optional).
	 * 
	 * @param limit    The maximum number of popular repositories to retrieve.
	 * 
	 * @return The search URL as a string.
	 */
	private String buildSearchUrl(LocalDate date, String language, int limit) {
		StringBuilder sb = new StringBuilder();
		sb.append(gitHubApiUrl);
		sb.append("/search/repositories?q=created:>");
		sb.append(date.format(DateTimeFormatter.ISO_LOCAL_DATE));

		if (language != null && !language.isEmpty()) {
			sb.append("+language:");
			sb.append(language);
		}

		sb.append("&sort=stars&order=desc");
		sb.append("&per_page=").append(limit);

		return sb.toString();
	}

}