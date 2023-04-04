package com.shop.apotheke.model;

public class GitHubRepo {
	private long id;
	private String name;
	private String description;
	private String html_url;
	private int stargazers_count;
	private String language;
	private String created_at;

	public GitHubRepo() {

	}

	public GitHubRepo(long id, String name, String description, String html_url, int stargazers_count, String language,
			String created_at) {

		this.id = id;
		this.name = name;
		this.description = description;
		this.html_url = html_url;
		this.stargazers_count = stargazers_count;
		this.language = language;
		this.created_at = created_at;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHtml_url() {
		return html_url;
	}

	public void setHtml_url(String html_url) {
		this.html_url = html_url;
	}

	public int getStargazers_count() {
		return stargazers_count;
	}

	public void setStargazers_count(int stargazers_count) {
		this.stargazers_count = stargazers_count;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

}
