package com.gopaychain.thork.controller;


import com.gopaychain.thork.container.OrchestrationExecutor;
import com.gopaychain.thork.entity.ThorkStatus;
import com.gopaychain.thork.repository.ThorkStatusRepository;
import com.gopaychain.thork.service.DecisionObjectLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.gopaychain.thork.entity.
import java.io.IOException;

@RestController
public class ThorkController {

    @Autowired
    ThorkStatusRepository thorkStatusRepository;

    @Autowired
    DecisionObjectLocator decisionObjectLocator;


    @PostMapping(value="/queue/thork/{name}")
    public Mono<ThorkStatus> queue(@RequestParam String name) throws IOException {
        com.gopaychain.thork.entity.thork.Decision decision = decisionObjectLocator.getDecisionObjectByName(name);
        OrchestrationExecutor.execute(decision);
    }


    @GetMapping(value="/thork")
    public Flux<ThorkStatus> getAllRunStatus(@RequestBody ThorkStatus thorkStatus)
    {
        return thorkStatusRepository.findAll();
    }

}
