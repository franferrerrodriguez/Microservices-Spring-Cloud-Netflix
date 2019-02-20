package com.springboot.microservice.ms.client.management.caller;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class Caller {

	public <T> ResponseEntity<List<T>> getApi(final String path, final HttpMethod method) {

		final RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<List<T>> response;

		try {
			response = restTemplate.exchange(path, method, null, new ParameterizedTypeReference<List<T>>() {
			});
		} catch (HttpClientErrorException e) {
			response = new ResponseEntity<List<T>>(HttpStatus.NOT_FOUND);
		}

		return response;
	}

}