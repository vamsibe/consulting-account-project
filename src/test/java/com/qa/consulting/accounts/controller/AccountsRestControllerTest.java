package com.qa.consulting.accounts.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.consulting.accounts.model.Account;
import com.qa.consulting.accounts.service.AccountService;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountsRestController.class)
public class AccountsRestControllerTest {

	 @Autowired
	 private MockMvc mvc;
	 
	 @MockBean
	 private AccountService service;
	 
	    @Test
	    public void whenPostAccount_thenCreateEmployee() throws Exception {
	    	Account alex = new Account(1L, "alex");
	        given(service.save(Mockito.anyObject())).willReturn(alex);

	        mvc.perform(post("/account-project/rest/accounts").contentType(MediaType.APPLICATION_JSON).content(toJson(alex))).andExpect(status().isCreated()).andExpect(jsonPath("$.firstName", is("alex")));
	        verify(service, VerificationModeFactory.times(1)).save(Mockito.anyObject());
	        reset(service);
	    }

	    @Test
	    public void givenEmployees_whenGetEmployees_thenReturnJsonArray() throws Exception {
	    	Account alex = new Account(1L, "alex");
	    	Account john = new Account(2L,"john");
	    	Account bob = new Account(3L, "bob");

	        List<Account> allEmployees = Arrays.asList(alex, john, bob);

	        given(service.getAllAccounts()).willReturn(allEmployees);

	        mvc.perform(get("/account-project/rest/accounts").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(3))).andExpect(jsonPath("$[0].firstName", is(alex.getFirstName()))).andExpect(jsonPath("$[1].firstName", is(john.getFirstName())))
	                .andExpect(jsonPath("$[2].firstName", is(bob.getFirstName())));
	        verify(service, VerificationModeFactory.times(1)).getAllAccounts();
	        reset(service);
	    }
	    
	    private byte[] toJson(Object object) throws IOException {
	        ObjectMapper mapper = new ObjectMapper();
	        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	        return mapper.writeValueAsBytes(object);
	    }

}
