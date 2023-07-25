package com.kezlo.cards.controller;
import java.util.List;
import java.util.Properties;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.kezlo.cards.config.CardsServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kezlo.cards.model.Cards;
import com.kezlo.cards.model.Customer;
import com.kezlo.cards.repository.CardsRepository;

@RestController
@RequestMapping("/cards")
public class CardsController {

    @Autowired
    private CardsRepository cardsRepository;
    @Autowired
    private CardsServiceConfig cardConfig;

    @PostMapping("/myCards")
    public List<Cards> getCardDetails(@RequestBody Customer customer) {
        return cardsRepository.findByCustomerId(customer.getCustomerId());

    }

    @GetMapping("/properties")
    public String getPropertyDetail () throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties();
        properties.put("msg", cardConfig.getMsg());
        properties.put("buildVersion", cardConfig.getBuildVersion());
        properties.put("mailDetail", cardConfig.getMailDetails());
        properties.put("activeBranches", cardConfig.getActiveBranches());
        return ow.writeValueAsString(properties);
    }

}
