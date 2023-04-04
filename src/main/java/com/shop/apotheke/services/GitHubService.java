package com.shop.apotheke.services;

import java.time.LocalDate;
import java.util.List;

import com.shop.apotheke.model.GitHubRepo;

/**
 * This interface defines the contract for fetching popular GitHub repositories.
 */
public interface GitHubService {

	/**
	 * Fetches popular GitHub repositories created after the given date and matching
	 * the given language, limited by the specified number of results.
	 *
	 * @param date     the date after which to search for repositories (in
	 *                 ISO_LOCAL_DATE format)
	 * @param language the programming language to filter by, or null to return
	 *                 repositories of any language
	 * @param limit    the maximum number of repositories to return (default value
	 *                 is 10)
	 * @return a list of popular GitHub repositories
	 */
	List<GitHubRepo> getPopularRepositories(LocalDate date, String language, int limit);

}
