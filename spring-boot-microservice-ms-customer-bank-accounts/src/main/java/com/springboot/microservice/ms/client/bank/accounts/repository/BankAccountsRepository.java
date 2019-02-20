package com.springboot.microservice.ms.client.bank.accounts.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.springboot.microservice.ms.client.bank.accounts.entity.db.BankAccounts;

public interface BankAccountsRepository extends Repository<BankAccounts, Long> {

	public BankAccounts findById(Long id);

	public List<BankAccounts> findAll();

	public List<BankAccounts> findByNif(String nif);

}