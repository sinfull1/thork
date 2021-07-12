package com.gopaychain.thork.entity.model;

import java.util.LinkedHashMap;
import java.util.Random;

public class SelectDecision extends Decision {

    @Override
    public void execute(Decision currentDecision, LinkedHashMap<String, Object> results) {
        if (currentDecision.getDecisions() == null) {
            if (currentDecision.getAction().execute(results)) {

            } else {
                currentDecision.execute(currentDecision,results);
            }
        } else {
            if (currentDecision.getDecisions() != null) {
                if (currentDecision.getAction().execute(results)) {

                    Decision nextDecision = currentDecision.getDecisions().get(new Random().nextInt(currentDecision.getDecisions().size()));
                    nextDecision.execute(nextDecision,results);
                } else {
                    currentDecision.execute(currentDecision,results);
                }
            }


        }
    }
}

