package com.gopaychain.thork.entity.thork;

import java.util.LinkedHashMap;

public class AllDecision extends Decision {

    @Override
    public void execute(Decision currentDecision, LinkedHashMap<String, Object> results) {
        if (currentDecision.getDecisions() == null) {

            if (currentDecision.getAction().execute(results)) {
            } else {
                currentDecision.execute(currentDecision,results);
            }
        } else {
            if (currentDecision.getAction().execute(results)) {
                for (Decision decision : currentDecision.getDecisions()) {
                    decision.execute(decision,results);
                }
            } else {
                currentDecision.execute(currentDecision,results);
            }
        }
    }
}

