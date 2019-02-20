package com.springboot.microservice.ms.client.bank.accounts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.springboot.microservice.ms.client.bank.accounts.entity.db.BankAccounts;
import com.springboot.microservice.ms.client.bank.accounts.repository.BankAccountsRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class MsCustomerBankAccountsApplicationTests {

	@Autowired
	private BankAccountsRepository repository;

	@Test
	public void contextLoads() {

	}

	@Test
	public void testId_OK() {
		BankAccounts bankAccount = repository.findById(1L);
		Assert.assertEquals(bankAccount.getId(), 1L, 0);
	}

	@Test
	public void testLog() {
		Log4J2PropertiesConf log4J2PropertiesConf = new Log4J2PropertiesConf();
		log4J2PropertiesConf.performSomeTask();
	}

}

class Log4J2PropertiesConf {
	private static Logger logger = LogManager.getLogger(SpringJUnit4ClassRunner.class);

	public void performSomeTask() {
		logger.debug("This is a debug message");
		logger.info("This is an info message");
		logger.warn("This is a warn message");
		logger.error("This is an error message");
		logger.fatal("This is a fatal message");
	}
}
