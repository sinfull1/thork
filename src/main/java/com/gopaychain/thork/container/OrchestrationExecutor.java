package com.gopaychain.thork.container;

import com.gopaychain.thork.entity.thork.Decision;

import java.util.LinkedHashMap;

public class OrchestrationExecutor {

    public  static LinkedHashMap<String, Object> results = new LinkedHashMap<String,Object>();

    public static void execute(Decision decision) {
        decision.execute(decision,results);
    }
}



