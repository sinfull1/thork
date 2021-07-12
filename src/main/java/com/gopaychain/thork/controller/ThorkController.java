package com.gopaychain.thork.controller;


import com.gopaychain.thork.container.OrchestrationExecutor;

import com.gopaychain.thork.model.Decision;
import com.gopaychain.thork.service.DecisionObjectLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
public class ThorkController {



    @Autowired
    DecisionObjectLocator decisionObjectLocator;


    @GetMapping(value="/queue/thork/{name}")
    public Mono<String> queue(@PathVariable("name") String name) throws IOException, ExecutionException, InterruptedException {
        Decision decision = decisionObjectLocator.getDecisionObjectByName(name);
        OrchestrationExecutor orchestrationExecutor = new OrchestrationExecutor();
        orchestrationExecutor.execute(decision);
        return Mono.just("Queued");
    }



}
