package com.gopaychain.thork.controller;


import com.gopaychain.thork.container.OrchestrationExecutor;
import com.gopaychain.thork.execution.ExecutionEngine;
import com.gopaychain.thork.execution.ExecutionService;
import com.gopaychain.thork.model.Decision;
import com.gopaychain.thork.model.ThorkStatus;
import com.gopaychain.thork.repository.ThorkStatusRepository;
import com.gopaychain.thork.service.DecisionObjectLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RestController
public class ThorkController {

    @Autowired
    ExecutionEngine executionEngine;

    @Autowired
    DecisionObjectLocator decisionObjectLocator;

    @Autowired
    ExecutionService executionService;


    @GetMapping(value="/queue/thork/{name}")
    public Mono<Object> queue(@PathVariable("name") String name) throws IOException {
        Decision decision = decisionObjectLocator.getDecisionObjectByName(name);
        executionEngine.queueExecution(Mono.just(decision));
        return Mono.just("Queued");
    }


    @GetMapping(value="/queue/thork/results", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Object> queue() throws IOException {
        return executionService.getFlux();
    }

}
