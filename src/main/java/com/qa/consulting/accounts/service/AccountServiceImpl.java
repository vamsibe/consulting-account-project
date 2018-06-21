package com.qa.consulting.accounts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.qa.consulting.accounts.model.Account;
import com.qa.consulting.accounts.repository.AccountRepository;

public class AccountServiceImpl implements AccountService {
	
	@Autowired
	AccountRepository accountRepository;

	@Override
	public Optional<Account> getAccountById(Long id) {
		return accountRepository.findById(id);
	}

	@Override
	public Optional<Account> getAccountByFirstName(String firstName) {
		return accountRepository.findByFirstName(firstName);
	}

	@Override
	public Optional<Account> getAccountByLastName(String lastName) {
		return accountRepository.findByLastName(lastName);
	}

	@Override
	public List<Account> getAllAccounts() {
		return accountRepository.findAll();
	}

	@Override
	public Account save(Account account) {
		return accountRepository.save(account);
	}

}
