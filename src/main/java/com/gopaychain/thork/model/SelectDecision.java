package com.gopaychain.thork.model;

import reactor.core.publisher.Mono;

import java.util.LinkedHashMap;
import java.util.Random;

public class SelectDecision extends Decision {

    @Override
    public Mono execute(Decision currentDecision, LinkedHashMap<String, Object> results) {
        if (currentDecision.getDecisions() == null) {
           return currentDecision.getAction().execute(results).log().thenReturn(Mono.empty());
        } else {
            if (currentDecision.getDecisions() != null) {
                return currentDecision.getAction().execute(results).log().then(currentDecision.execute(currentDecision.getDecisions().get(new Random().nextInt(currentDecision.getDecisions().size())),results));
            }
        }

        return null;
    }
}

