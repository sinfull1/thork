package com.gopaychain.thork.container;

import com.gopaychain.thork.model.Decision;

import java.util.LinkedHashMap;
import java.util.concurrent.ExecutionException;

public class OrchestrationExecutor {

    public  static LinkedHashMap<String, Object> results = new LinkedHashMap<String,Object>();

    public static void execute(Decision decision) throws ExecutionException, InterruptedException {
        decision.execute(decision,results);
    }
}



