package com.kezlo.accounts.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.kezlo.accounts.config.AccountsServiceConfig;
import com.kezlo.accounts.model.Accounts;
import com.kezlo.accounts.model.Customer;
import com.kezlo.accounts.repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;


@RestController
@RequestMapping("/accounts")
public class AccountsController {

    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    private AccountsServiceConfig accountsConfig;

    @PostMapping()
    public Accounts getAccountDetails(@RequestBody Customer customer) {

        return accountsRepository.findByCustomerId(customer.getCustomerId());

    }

    @GetMapping("/properties")
    public String getPropertyDetail () throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties();
        properties.put("msg", accountsConfig.getMsg());
        properties.put("buildVersion", accountsConfig.getBuildVersion());
        properties.put("mailDetail", accountsConfig.getMailDetails());
        properties.put("activeBranches", accountsConfig.getActiveBranches());
        return ow.writeValueAsString(properties);
    }

}
