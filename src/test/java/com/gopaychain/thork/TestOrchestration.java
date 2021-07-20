package com.gopaychain.thork;

import com.gopaychain.thork.container.OrchestrationExecutor;
import com.gopaychain.thork.model.Decision;
import com.gopaychain.thork.service.DecisionObjectLocator;
import com.gopaychain.thork.service.NodeTraversalService;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

class TestOrchestration {

    DecisionObjectLocator decisionObjectLocator = new DecisionObjectLocator();

    @Test
    public void testDumbExecute() throws IOException, ExecutionException, InterruptedException {
        OrchestrationExecutor orchestrationExecutor = new OrchestrationExecutor();
        orchestrationExecutor.execute(decisionObjectLocator.getDecisionObjectByName("dumbTree"));
        //orchestrationExecutor.getResults().entrySet().forEach(System.out::println);
        orchestrationExecutor.getRollback().entrySet().forEach(System.out::println);
    }
    @Test
    public void testSmartExecute() throws IOException, ExecutionException, InterruptedException {
        OrchestrationExecutor orchestrationExecutor = new OrchestrationExecutor();
        orchestrationExecutor.execute(decisionObjectLocator.getDecisionObjectByName("smartTree"));
        orchestrationExecutor.getResults().entrySet().forEach(System.out::println);
    }

    @Test
    public void testHybridExecute() throws IOException, ExecutionException, InterruptedException {
        OrchestrationExecutor orchestrationExecutor = new OrchestrationExecutor();
        orchestrationExecutor.execute(decisionObjectLocator.getDecisionObjectByName("hybridTree"));
        orchestrationExecutor.getResults().entrySet().forEach(System.out::println);
    }


    @Test
    public void testFetchNode() throws IOException {
        Decision decision = decisionObjectLocator.getDecisionObjectByName("hybridTree");
        NodeTraversalService nodeTraversalService = new NodeTraversalService();
        Decision d = nodeTraversalService.fetchNode(decision,1,2);
        assert(d.getLevel() ==1);
        assert(d.getNum() ==2);



    }

}
