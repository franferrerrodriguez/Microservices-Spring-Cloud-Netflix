package com.springboot.microservice.ms.client.bank.accounts.controller;

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
import com.springboot.microservice.ms.client.bank.accounts.MsCustomerBankAccountsApplication;
import com.springboot.microservice.ms.client.bank.accounts.entity.db.BankAccounts;
import com.springboot.microservice.ms.client.bank.accounts.repository.BankAccountsRepository;
import com.springboot.microservice.ms.client.bank.accounts.service.IBankAccountsService;

@RestController
public class Controller {

	@Autowired
	private Environment environment;

	@Autowired
	IBankAccountsService bankAccountService;

	@Autowired
	private BankAccountsRepository repository;

	private static final Logger LOGGER = LoggerFactory.getLogger(MsCustomerBankAccountsApplication.class);

	@RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
	@HystrixCommand()
	public ResponseEntity<BankAccounts> bankAccountsById(@PathVariable Long id) {

		String port = environment.getProperty("local.server.port");

		LOGGER.info(String.format("Called endpoint: 'bankAccountsById' | Port: '%s'", port));

		BankAccounts bankAccount = bankAccountService.bankAccountsById(id);

		return new ResponseEntity<BankAccounts>(bankAccount, HttpStatus.OK);

	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@HystrixCommand()
	public ResponseEntity<List<BankAccounts>> bankAccountsAll() {

		String port = environment.getProperty("local.server.port");

		LOGGER.info(String.format("Called endpoint: 'bankAccountsAll' | Port: '%s'", port));

		List<BankAccounts> bankAccounts = repository.findAll();

		return new ResponseEntity<List<BankAccounts>>(bankAccounts, HttpStatus.OK);
	}

	@RequestMapping(value = "/nif/{nif}", method = RequestMethod.GET)
	@HystrixCommand()
	public ResponseEntity<List<BankAccounts>> bankAccountsByNif(@PathVariable String nif) {

		String port = environment.getProperty("local.server.port");

		LOGGER.info(String.format("Called endpoint: 'bankAccountsByNif' | Port: '%s'", port));

		List<BankAccounts> bankAccounts = repository.findByNif(nif);

		return new ResponseEntity<List<BankAccounts>>(bankAccounts, HttpStatus.OK);
	}

}