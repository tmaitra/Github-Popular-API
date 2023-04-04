package com.shop.apotheke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.shop.apotheke.model.GitHubRepo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GitHubControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void getPopularRepositories_returnsRepositories() {
        LocalDate date = LocalDate.now().minusDays(7);
        String url = "/api/popular-repos?date=" + date.toString() + "&limit=5";
        ResponseEntity<List<GitHubRepo>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<GitHubRepo>>() {});

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(5, response.getBody().size());
    }

    @Test
    void getPopularRepositories_withLanguage_returnsRepositories() {
        LocalDate date = LocalDate.now().minusDays(7);
        String language = "Java";
        String url = "/api/popular-repos?date=" + date.toString() + "&language=" + language + "&limit=5";
        ResponseEntity<List<GitHubRepo>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<GitHubRepo>>() {});

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(5, response.getBody().size());
        assertTrue(response.getBody().stream().allMatch(repo -> repo.getLanguage().equalsIgnoreCase(language)));
    }

    @Test
    void getPopularRepositories_withInvalidDate_returnsBadRequest() {
        String invalidDate = "2021-02-30";
        String url = "/api/popular-repos?date=" + invalidDate + "&limit=5";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}