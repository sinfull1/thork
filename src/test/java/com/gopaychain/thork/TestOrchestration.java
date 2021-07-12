package com.gopaychain.thork;

import com.gopaychain.thork.container.OrchestrationExecutor;
import com.gopaychain.thork.service.DecisionObjectLocator;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

class TestOrchestration {

    DecisionObjectLocator decisionObjectLocator = new DecisionObjectLocator();

    @Test
    public void testDumbExecute() throws IOException, ExecutionException, InterruptedException {
        OrchestrationExecutor.execute(decisionObjectLocator.getDecisionObjectByName("dumbTree"));
        OrchestrationExecutor.results.entrySet().forEach(System.out::println);
    }
    @Test
    public void testSmartExecute() throws IOException, ExecutionException, InterruptedException {

        OrchestrationExecutor.execute(decisionObjectLocator.getDecisionObjectByName("smartTree"));
        OrchestrationExecutor.results.entrySet().forEach(System.out::println);
    }

    @Test
    public void testHybridExecute() throws IOException, ExecutionException, InterruptedException {
        OrchestrationExecutor.execute(decisionObjectLocator.getDecisionObjectByName("hybridTree"));
        OrchestrationExecutor.results.entrySet().forEach(System.out::println);
    }
}
