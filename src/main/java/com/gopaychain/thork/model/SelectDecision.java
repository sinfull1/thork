package com.gopaychain.thork.model;

import com.gopaychain.thork.model.Decision;

import java.util.LinkedHashMap;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class SelectDecision extends Decision {

    @Override
    public void execute(Decision currentDecision, LinkedHashMap<String, Object> results) throws ExecutionException, InterruptedException {
        if (currentDecision.getDecisions() == null) {
            if (currentDecision.getAction().execute(results)) {

            } else {
                currentDecision.getCallback().execute(results);
                currentDecision.execute(currentDecision,results);
            }
        } else {
            if (currentDecision.getDecisions() != null) {
                if (currentDecision.getAction().execute(results)) {

                    Decision nextDecision = currentDecision.getDecisions().get(new Random().nextInt(currentDecision.getDecisions().size()));
                    nextDecision.execute(nextDecision,results);
                } else {
                    currentDecision.getCallback().execute(results);
                    currentDecision.execute(currentDecision,results);
                }
            }


        }
    }
}

