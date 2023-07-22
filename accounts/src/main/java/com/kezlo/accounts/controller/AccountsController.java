package com.kezlo.accounts.controller;

import com.kezlo.accounts.model.Accounts;
import com.kezlo.accounts.model.Customer;
import com.kezlo.accounts.repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountsController {

    @Autowired
    private AccountsRepository accountsRepository;

    @PostMapping()
    public Accounts getAccountDetails(@RequestBody Customer customer) {

        return accountsRepository.findByCustomerId(customer.getCustomerId());

    }

}
