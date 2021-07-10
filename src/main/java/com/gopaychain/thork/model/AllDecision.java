package com.gopaychain.thork.model;

import com.gopaychain.thork.container.OrchestrationExecutor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.LinkedHashMap;

public class AllDecision extends Decision {

    @Override
    public Mono execute(Decision currentDecision, LinkedHashMap<String, Object> results) {
        if (currentDecision.getDecisions() == null) {
           return currentDecision.getAction().execute(results).log().thenReturn(Mono.empty());
       } else {
            return currentDecision.getAction().execute(results).flatMap(res->{
            for (Decision decision: currentDecision.getDecisions())
            {
                decision.execute(decision, results);
            }
            return Mono.empty();
        });

        }
    }
}

