package com.shop.apotheke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.shop.apotheke.exception.GitHubApiException;
import com.shop.apotheke.model.GitHubRepo;
import com.shop.apotheke.model.GitHubRepoSearchResponse;
import com.shop.apotheke.services.GitHubServiceImpl;

class GitHubServiceImplTest {

	private static final String GITHUB_API_URL = "https://api.github.com";
	private static final int LIMIT = 10;

	@Mock
	private RestTemplate restTemplate;

	private GitHubServiceImpl gitHubService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		gitHubService = new GitHubServiceImpl(restTemplate, GITHUB_API_URL);
	}

	@Test
	void getPopularRepositories_returnsExpectedResult_whenApiReturnsSuccessfulResponse() {
		// Arrange
		String language = "Java";
		LocalDate date = LocalDate.now();
		List<GitHubRepo> expectedPopularRepos = new ArrayList<>();
		expectedPopularRepos.add(new GitHubRepo());
		expectedPopularRepos.add(new GitHubRepo());
		expectedPopularRepos.add(new GitHubRepo());
		expectedPopularRepos.add(new GitHubRepo());
		expectedPopularRepos.add(new GitHubRepo());
		expectedPopularRepos.add(new GitHubRepo());
		expectedPopularRepos.add(new GitHubRepo());
		expectedPopularRepos.add(new GitHubRepo());
		expectedPopularRepos.add(new GitHubRepo());
		expectedPopularRepos.add(new GitHubRepo());
		GitHubRepoSearchResponse response = new GitHubRepoSearchResponse();
		response.setItems(expectedPopularRepos);

		when(restTemplate.getForEntity(any(String.class), eq(GitHubRepoSearchResponse.class)))
				.thenReturn(new ResponseEntity<>(response, HttpStatus.OK));

		// Act
		List<GitHubRepo> actualPopularRepos = gitHubService.getPopularRepositories(date, language, LIMIT);

		// Assert
		verify(restTemplate).getForEntity(any(String.class), eq(GitHubRepoSearchResponse.class));
		assertEquals(LIMIT, actualPopularRepos.size());
		assertIterableEquals(expectedPopularRepos.stream().limit(LIMIT).collect(Collectors.toList()),
				actualPopularRepos);
	}

	@Test
	void getPopularRepositories_throwsException_whenApiReturnsUnsuccessfulResponse() {
		// Arrange
		String language = "Java";
		LocalDate date = LocalDate.now();
		when(restTemplate.getForEntity(any(String.class), eq(GitHubRepoSearchResponse.class)))
				.thenReturn(new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR));

		// Act & Assert
		assertThrows(GitHubApiException.class, () -> gitHubService.getPopularRepositories(date, language, LIMIT));

		verify(restTemplate).getForEntity(any(String.class), eq(GitHubRepoSearchResponse.class));
	}

}
