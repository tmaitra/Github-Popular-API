package com.shop.apotheke.config;

import java.io.IOException;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * Configuration class for RestTemplate.
 * 
 * Defines a bean for RestTemplate with an error handler that throws exceptions
 * for non-successful HTTP status codes.
 */
@Configuration
public class RestTemplateConfig {

	/**
	 * 
	 * Defines a bean for RestTemplate with a custom error handler that throws
	 * exceptions for non-successful HTTP status codes.
	 * 
	 * @param restTemplateBuilder RestTemplateBuilder instance to build RestTemplate
	 *                            bean.
	 * 
	 * @return RestTemplate bean with a custom error handler.
	 */
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.errorHandler(new ResponseErrorHandler() {
			@Override
			public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
				return !httpResponse.getStatusCode().is2xxSuccessful();
			}

			@Override
			public void handleError(ClientHttpResponse httpResponse) throws IOException {
				HttpStatusCode statusCode = httpResponse.getStatusCode();
				if (statusCode.is4xxClientError()) {
					throw new HttpClientErrorException(statusCode, httpResponse.getStatusText());
				} else if (statusCode.is5xxServerError()) {
					throw new HttpServerErrorException(statusCode, httpResponse.getStatusText());
				}
			}
		}).build();
	}
}