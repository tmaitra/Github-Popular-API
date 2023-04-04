# GitHub Popular Repositories API

This project is an API that provides information on popular repositories on GitHub. It retrieves data from the GitHub API and processes it to return a list of popular repositories based on certain criteria.

### Installation

To install and run the API, follow these steps:

* Make sure you have Java and Maven installed on your machine.
* Open a terminal and navigate to the project directory.
* Run the command mvn spring-boot:run to start the application.
* The API will now be running at http://localhost:8080/api.

### Usage

To use the API, send a GET request to the /api/popular-repos endpoint. The following query parameters are available:

* date (required): A date in the format YYYY-MM-DD, which specifies the date from which to start searching for repositories.
* language (optional): A language name or language code to filter the search results by.
* limit (optional): The maximum number of results to return. The default is 10.

Here is an example request URL:

```
http://localhost:8080/api/popular-repos?date=2022-03-01&language=java&limit=20
```

The API will return a JSON array of repository objects, which contain information such as the repository name, URL, description, and number of stars.