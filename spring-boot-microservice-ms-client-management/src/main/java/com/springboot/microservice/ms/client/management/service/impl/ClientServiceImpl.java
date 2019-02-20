package com.springboot.microservice.ms.client.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.microservice.ms.client.management.caller.Caller;
import com.springboot.microservice.ms.client.management.entity.BankAccounts;
import com.springboot.microservice.ms.client.management.entity.db.Client;
import com.springboot.microservice.ms.client.management.repository.IClientRepository;
import com.springboot.microservice.ms.client.management.service.IClientService;

@Service
public class ClientServiceImpl implements IClientService {

	@Autowired
	private Caller caller;

	@Autowired
	IClientRepository clientRepository;

	@Autowired
	private Environment environment;

	public Client clientById(Long id) throws InterruptedException {

		Thread.sleep(0);
		String port = environment.getProperty("local.server.port");
		if (port.equals("8001")) {
			Thread.sleep(25000);
		}

		Client client = clientRepository.findById(id);

		ResponseEntity<List<BankAccounts>> response = this.caller.getApi("http://localhost:8000/nif/" + client.getNif(),
				HttpMethod.GET);

		client.setBankAccounts(response.getBody());

		return client;
	}

	public List<Client> clientAll() throws InterruptedException {

		List<Client> clients = clientRepository.findAll();

		for (Client client : clients) {

			ResponseEntity<List<BankAccounts>> response = this.caller
					.getApi("http://localhost:8000/nif/" + client.getNif(), HttpMethod.GET);

			System.out.println(response.getStatusCode());

			client.setBankAccounts(response.getBody());
		}

		return clients;
	}

}