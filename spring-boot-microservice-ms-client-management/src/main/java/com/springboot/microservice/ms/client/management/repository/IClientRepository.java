package com.springboot.microservice.ms.client.management.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.springboot.microservice.ms.client.management.entity.db.Client;

public interface IClientRepository extends Repository<Client, Long> {

	public Client findById(Long id);

	public List<Client> findAll();

}