package com.gopaychain.thork.model;

import com.gopaychain.thork.execution.ExecutionResultsService;
import com.gopaychain.thork.service.ApiExecutionService;
import io.vavr.Tuple2;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.util.HashMap;

public class ApiAction extends Action {


    @Override
    public Mono<Boolean> execute(HashMap<String, Object> results) {
        System.out.println("Executing API action " + super.getId());
        if (true) {
            return  ApiExecutionService.execute(this).flatMap(k -> {
                ExecutionResultsService.getSink().emitNext(new Tuple2<>(this.getId(), k), Sinks.EmitFailureHandler.FAIL_FAST);
                return Mono.empty();
            }).thenReturn(true);


        } else {
            results.put(this.getId(), "Action API Failed ");
            return Mono.just(false);
        }

    }
}
