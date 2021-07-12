package com.gopaychain.thork.container;

import com.gopaychain.thork.model.Decision;

import java.util.LinkedHashMap;
import java.util.concurrent.ExecutionException;

public class OrchestrationExecutor {

    public LinkedHashMap<String, Object> getResults() {
        return results;
    }

    private  LinkedHashMap<String, Object> results = new LinkedHashMap<>();

    public LinkedHashMap<String, Object> getRollback() {
        return rollback;
    }

    private  LinkedHashMap<String, Object> rollback = new LinkedHashMap<>();

    public void execute(Decision decision) throws ExecutionException, InterruptedException {
        decision.execute(decision,results,rollback);
    }

}



