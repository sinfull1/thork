package com.gopaychain.thork;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gopaychain.thork.container.OrchestrationExecutor;
import com.gopaychain.thork.entity.thork.Decision;
import com.gopaychain.thork.service.DecisionObjectLocator;
import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
}
