package com.gopaychain.matching.container;

import com.gopaychain.matching.entity.thork.Decision;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class OrchestrationExecutor {

    public  static LinkedHashMap<String, Object> results = new LinkedHashMap<String,Object>();

    public static void execute(Decision decision) {
        decision.execute(decision,results);
    }
}



