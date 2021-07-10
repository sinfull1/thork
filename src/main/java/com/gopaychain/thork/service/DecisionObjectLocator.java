package com.gopaychain.thork.service;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gopaychain.thork.container.OrchestrationExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
@Component

public class DecisionObjectLocator {
    ObjectMapper objectMapper = new ObjectMapper();

    public com.gopaychain.thork.entity.thork.Decision getDecisionObjectByName(String name) throws IOException {

        File file = ResourceUtils.getFile("classpath:"+name+".json");
        String json = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        return objectMapper.readValue(json, com.gopaychain.thork.entity.thork.Decision.class);

    }



}
