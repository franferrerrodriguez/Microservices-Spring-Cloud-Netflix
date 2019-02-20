package com.springboot.microservice.ms.client.management.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springboot.microservice.ms.client.management.entity.db.Client;
import com.springboot.microservice.ms.client.management.repository.IClientRepository;
import com.springboot.microservice.ms.client.management.service.IClientService;

@RestController
public class Controller {

	@Autowired
	private Environment environment;

	@Autowired
	private IClientService clientService;

	@Autowired
	private IClientRepository repository;

	private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

	@RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
	@HystrixCommand()
	public ResponseEntity<Client> clientById(@PathVariable Long id) throws InterruptedException {

		String port = environment.getProperty("local.server.port");

		LOGGER.info(String.format("Called endpoint: 'clientById' | Port: '%s'", port));

		Client client = clientService.clientById(id);

		return new ResponseEntity<Client>(client, HttpStatus.OK);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@HystrixCommand()
	public ResponseEntity<List<Client>> clientAll() throws InterruptedException {

		String port = environment.getProperty("local.server.port");

		LOGGER.info(String.format("Called endpoint: 'clientAll' | Port: '%s'", port));

		List<Client> clients = clientService.clientAll();

		return new ResponseEntity<List<Client>>(clients, HttpStatus.OK);
	}

}
