package com.qa.consulting.accounts.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.consulting.accounts.model.Account;

public interface AccountRepository  extends JpaRepository<Account, Long> {
	
	 public Optional<Account> findByFirstName(String firstName);
	 public Optional<Account> findByLastName(String lastName);

}
