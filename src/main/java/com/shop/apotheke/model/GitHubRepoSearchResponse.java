package com.shop.apotheke.model;

import java.util.List;

public class GitHubRepoSearchResponse {

    private int total_count;
    private boolean incomplete_results;
    private List<GitHubRepo> items;

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public boolean isIncomplete_results() {
        return incomplete_results;
    }

    public void setIncomplete_results(boolean incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public List<GitHubRepo> getItems() {
        return items;
    }

    public void setItems(List<GitHubRepo> items) {
        this.items = items;
    }
}
