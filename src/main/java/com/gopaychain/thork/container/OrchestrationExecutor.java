package com.gopaychain.thork.container;

import com.gopaychain.thork.model.Decision;

import java.util.LinkedHashMap;

public class OrchestrationExecutor {

    public  static LinkedHashMap<String, Object> results = new LinkedHashMap<String,Object>();

    public static LinkedHashMap<String, Object> execute(Decision decision) {
        decision.execute(decision,results).subscribe(System.out::println);
        return results;
    }
    public static void execute(Decision decision,LinkedHashMap<String, Object> results) {
        decision.execute(decision,results).subscribe(System.out::println);
    }
}



