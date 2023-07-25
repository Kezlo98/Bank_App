package com.kezlo.loans.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.kezlo.loans.config.LoansServiceConfig;
import com.kezlo.loans.model.Customer;
import com.kezlo.loans.model.Loans;
import com.kezlo.loans.repository.LoansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Properties;

@RestController
@RequestMapping("/loans")
public class LoansController {

    @Autowired
    private LoansRepository loansRepository;

    @Autowired
    private LoansServiceConfig loansConfig;

    @PostMapping("/myLoans")
    public List<Loans> getLoansDetails(@RequestBody Customer customer) {
        return loansRepository.findByCustomerIdOrderByStartDtDesc(customer.getCustomerId());
    }

    @GetMapping("/properties")
    public String getPropertyDetail () throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties();
        properties.put("msg", loansConfig.getMsg());
        properties.put("buildVersion", loansConfig.getBuildVersion());
        properties.put("mailDetail", loansConfig.getMailDetails());
        properties.put("activeBranches", loansConfig.getActiveBranches());
        return ow.writeValueAsString(properties);
    }
}
