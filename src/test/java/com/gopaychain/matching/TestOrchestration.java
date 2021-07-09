package com.gopaychain.matching;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gopaychain.matching.container.OrchestrationExecutor;
import com.gopaychain.matching.entity.thork.ApiAction;
import com.gopaychain.matching.entity.thork.Decision;
import org.junit.jupiter.api.Test;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;

class TestOrchestration {



    @Test
    public void testDumbExecute() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = ResourceUtils.getFile("classpath:dumbTree.json");
        String json = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        Decision decision = objectMapper.readValue(json, Decision.class);
        OrchestrationExecutor.execute(decision);
        OrchestrationExecutor.results.entrySet().forEach(System.out::println);
    }
    @Test
    public void testSmartExecute() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = ResourceUtils.getFile("classpath:smartTree.json");
        String json = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        Decision decision = objectMapper.readValue(json, Decision.class);
        OrchestrationExecutor.execute(decision);
        OrchestrationExecutor.results.entrySet().forEach(System.out::println);
    }

    @Test
    public void testHybridExecute() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = ResourceUtils.getFile("classpath:hybridTree.json");
        String json = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        Decision decision = objectMapper.readValue(json, Decision.class);
        OrchestrationExecutor.execute(decision);
        OrchestrationExecutor.results.entrySet().forEach(System.out::println);
    }
}
