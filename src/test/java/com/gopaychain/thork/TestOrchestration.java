package com.gopaychain.thork;

import com.gopaychain.thork.container.OrchestrationExecutor;
import com.gopaychain.thork.service.DecisionObjectLocator;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

import java.io.IOException;

class TestOrchestration {

    DecisionObjectLocator decisionObjectLocator = new DecisionObjectLocator();

    @Test
    public void testDumbExecute() throws IOException {
        OrchestrationExecutor.execute(decisionObjectLocator.getDecisionObjectByName("dumbTree"));
        OrchestrationExecutor.results.entrySet().forEach(System.out::println);
    }
    @Test
    public void testSmartExecute() throws IOException {

        OrchestrationExecutor.execute(decisionObjectLocator.getDecisionObjectByName("smartTree"));
        OrchestrationExecutor.results.entrySet().forEach(System.out::println);
    }

    @Test
    public void testHybridExecute() throws IOException {
        OrchestrationExecutor.execute(decisionObjectLocator.getDecisionObjectByName("hybridTree"));
        OrchestrationExecutor.results.entrySet().forEach(System.out::println);
    }

    @Test
    public void test() throws IOException {
       Mono.just(true).flatMapMany(res->Flux.just("s","d","dehg","sdjh","sdjhg")).subscribe(System.out::println);
    }




}
