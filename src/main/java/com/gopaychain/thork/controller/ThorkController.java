package com.gopaychain.thork.controller;


import com.gopaychain.thork.container.OrchestrationExecutor;
import com.gopaychain.thork.entity.ThorkStatus;

import com.gopaychain.thork.service.DecisionObjectLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RestController
public class ThorkController {



    @Autowired
    DecisionObjectLocator decisionObjectLocator;


    @GetMapping(value="/queue/thork/{name}")
    public Mono<String> queue(@PathVariable("name") String name) throws IOException {
        com.gopaychain.thork.entity.thork.Decision decision = decisionObjectLocator.getDecisionObjectByName(name);
        OrchestrationExecutor.execute(decision);
        return Mono.just("Queued");
    }



}
