package com.qa.consulting.accounts.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.qa.consulting.accounts.AccountsApplication;
import com.qa.consulting.accounts.model.Account;
import com.qa.consulting.accounts.repository.AccountRepository;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = AccountsApplication.class)
@AutoConfigureMockMvc
public class AccountRestControllerIT {

	 @Autowired
	    private MockMvc mvc;

	    @Autowired
	    private AccountRepository repository;

	    
	    @Test
	    public void whenValidInput_thenCreateAccount() throws IOException, Exception {
	        Account bob = new Account(1L, "Tom");
	        mvc.perform(post("/account-project/rest/accounts")
	        		.contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(bob)))
	                .andExpect(status().isCreated())	                
	                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
	                .andExpect(jsonPath("$.firstName").value("Tom"));


	    }

	    @Test
	    public void givenEmployees_whenGetEmployees_thenStatus200() throws Exception {
	        createTestAccount(1L, "bob");
	        createTestAccount(2L, "alex");

	        // @formatter:off
	        mvc.perform(get("/account-project/rest/accounts").contentType(MediaType.APPLICATION_JSON))
	          .andDo(print())
	          .andExpect(status().isOk())
	          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
	          .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
	          .andExpect(jsonPath("$[0].firstName", is("bob")))
	          .andExpect(jsonPath("$[1].firstName", is("alex")));
	        // @formatter:on
	    }



	    private void createTestAccount(Long id, String name) {
	    	Account account = new Account(id, name);
	        repository.saveAndFlush(account);
	    }

}
