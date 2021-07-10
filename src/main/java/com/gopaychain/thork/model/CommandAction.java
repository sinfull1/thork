package com.gopaychain.thork.model;

import com.gopaychain.thork.execution.ExecutionService;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.util.HashMap;
import java.util.Random;

public class CommandAction extends Action{

    @Autowired
    ExecutionService executionService;

    @Override
    public Mono<Boolean> execute(HashMap<String,Object> results) {
        System.out.println("Executing Command action "+ super.getId());
        if(new Random().nextBoolean())
        {
            executionService.getSink().emitNext("Executing Command action ", Sinks.EmitFailureHandler.FAIL_FAST);
            return Mono.just(true);
        }
        else{
            results.put(getId(),"Action Command Failed "+ getId());
            return  Mono.just(false);
        }

    }
}
