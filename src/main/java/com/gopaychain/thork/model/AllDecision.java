package com.gopaychain.thork.model;

import java.util.LinkedHashMap;
import java.util.concurrent.ExecutionException;

public class AllDecision extends Decision {

    @Override
    public void execute(Decision currentDecision, LinkedHashMap<String, Object> results,
                        LinkedHashMap<String, Object> rollback) throws ExecutionException, InterruptedException {
        if (currentDecision.getDecisions() == null) {
            if (currentDecision.getAction().execute(results)) {
                rollback.put(currentDecision.getOnFail().getId(), currentDecision.getOnFail());
            } else {
                currentDecision.getOnFail().execute(results);
                currentDecision.execute(currentDecision, results, rollback);
            }
        } else {
            if (currentDecision.getAction().execute(results)) {
                rollback.put(currentDecision.getOnFail().getId(), currentDecision.getOnFail());
                for (Decision decision : currentDecision.getDecisions()) {
                    decision.execute(decision, results, rollback);
                }
            } else {
                currentDecision.getOnFail().execute(results);
                currentDecision.execute(currentDecision, results, rollback);
            }
        }
    }
}

