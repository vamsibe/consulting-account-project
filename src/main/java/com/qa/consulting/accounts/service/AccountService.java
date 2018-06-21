package com.qa.consulting.accounts.service;

import java.util.List;
import java.util.Optional;

import com.qa.consulting.accounts.model.Account;

public interface AccountService {
	
    public Optional<Account> getAccountById(Long id);

    public Optional<Account> getAccountByFirstName(String firstName);
    
    public Optional<Account> getAccountByLastName(String lastName);

    public List<Account> getAllAccounts();    

    public Account save(Account account);

}


