package com.gopaychain.thork.model;

import java.util.LinkedHashMap;
import java.util.concurrent.ExecutionException;

public class AllDecision extends Decision {

    @Override
    public void execute(Decision currentDecision, LinkedHashMap<String, Object> results) throws ExecutionException, InterruptedException {
        if (currentDecision.getDecisions() == null) {

            if (currentDecision.getAction().execute(results)) {
            } else {
                currentDecision.getCallback().execute(results);
                currentDecision.execute(currentDecision,results);
            }
        } else {
            if (currentDecision.getAction().execute(results)) {
                for (Decision decision : currentDecision.getDecisions()) {
                    decision.execute(decision,results);
                }
            } else {
                currentDecision.getCallback().execute(results);
                currentDecision.execute(currentDecision,results);
            }
        }
    }
}

