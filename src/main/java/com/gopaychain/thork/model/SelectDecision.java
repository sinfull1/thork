package com.gopaychain.thork.model;

import com.gopaychain.thork.model.Decision;

import java.util.LinkedHashMap;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class SelectDecision extends Decision {

    @Override
    public void execute(Decision currentDecision, LinkedHashMap<String, Object> results,LinkedHashMap<String, Object> rollback) throws ExecutionException, InterruptedException {
        if (currentDecision.getDecisions() == null) {
            if (currentDecision.getAction().execute(results)) {
                rollback.put(currentDecision.getOnFail().getId(),currentDecision.getOnFail());
            } else {
                currentDecision.getOnFail().execute(results);
                currentDecision.execute(currentDecision,results,rollback);
            }
        } else {
            if (currentDecision.getDecisions() != null) {
                if (currentDecision.getAction().execute(results)) {
                    Decision nextDecision = currentDecision.getDecisions().get(new Random().nextInt(currentDecision.getDecisions().size()));
                    rollback.put(currentDecision.getOnFail().getId(),currentDecision.getOnFail());
                    nextDecision.execute(nextDecision,results,rollback);
                } else {
                    currentDecision.getOnFail().execute(results);
                    currentDecision.execute(currentDecision,results,rollback);
                }
            }


        }
    }
}

