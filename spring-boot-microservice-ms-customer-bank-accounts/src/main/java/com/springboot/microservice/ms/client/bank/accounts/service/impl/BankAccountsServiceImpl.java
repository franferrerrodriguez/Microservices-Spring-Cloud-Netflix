package com.springboot.microservice.ms.client.bank.accounts.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.microservice.ms.client.bank.accounts.entity.db.BankAccounts;
import com.springboot.microservice.ms.client.bank.accounts.repository.BankAccountsRepository;
import com.springboot.microservice.ms.client.bank.accounts.service.IBankAccountsService;

@Service
public class BankAccountsServiceImpl implements IBankAccountsService {

	@Autowired
	private BankAccountsRepository repository;

	public BankAccounts bankAccountsById(Long id) {

		return repository.findById(id);

	}

	public List<BankAccounts> bankAccountsAll() {

		return repository.findAll();

	}

	public List<BankAccounts> bankAccountsByNif(String nif) {

		return repository.findByNif(nif);

	}

}