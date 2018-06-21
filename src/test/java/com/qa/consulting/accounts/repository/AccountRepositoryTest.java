package com.qa.consulting.accounts.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.consulting.accounts.model.Account;



@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {

	private final String FIRST_NAME = "Jon";
	
	
	@Autowired
	private AccountRepository repository;
	
	@Test
	public void testFindAll() {
		List<Account> accounts = repository.findAll();
		assertEquals(3,accounts.size());
	}

	@Test
	public void givenFirstNameInDBWhenFindOneByNameThenReturnOptionalWithAccount() {
		Optional<Account> account = repository.findByFirstName(FIRST_NAME);
		assertThat(account.isPresent()).isEqualTo(true);
		assertThat(account.get().getFirstName()).isEqualTo(FIRST_NAME);
	}
}
