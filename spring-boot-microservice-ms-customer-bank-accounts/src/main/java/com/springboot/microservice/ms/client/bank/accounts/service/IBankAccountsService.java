package com.springboot.microservice.ms.client.bank.accounts.service;

import java.util.List;

import com.springboot.microservice.ms.client.bank.accounts.entity.db.BankAccounts;

public interface IBankAccountsService {

	BankAccounts bankAccountsById(Long id);

	List<BankAccounts> bankAccountsAll();

	List<BankAccounts> bankAccountsByNif(String nif);
}