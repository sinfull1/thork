package com.gopaychain.matching.controller;


import com.gopaychain.matching.container.MatchingDataContainer;
import com.gopaychain.matching.entity.ticket.Intent;
import com.gopaychain.matching.entity.ticket.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class CrudController {


    final MatchingDataContainer matchingDataContainer;

    @Autowired
    public CrudController(MatchingDataContainer matchingDataContainer) {
        this.matchingDataContainer = matchingDataContainer;
    }

    @PostMapping(value = "/ticket")
    public void addTicket(@RequestBody Ticket ticket) {
          matchingDataContainer.insertTicket(ticket);
    }

    @PostMapping(value = "/buy/intent")
    public void addBuyIntent(@RequestBody Intent intent) {
        matchingDataContainer.insertBuyIntent(intent);
    }

    @PostMapping(value = "/sell/intent")
    public void addSellIntent(@RequestBody Intent intent) {
        matchingDataContainer.insertSellIntent(intent);
    }

    @GetMapping(value = "/print")
    public String print() {
        return matchingDataContainer.toString();
    }

    @GetMapping(value = "/match")
    public String matches() {
        return matchingDataContainer.runMatches();
    }
}
