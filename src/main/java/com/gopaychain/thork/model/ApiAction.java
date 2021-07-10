package com.gopaychain.thork.model;

import com.gopaychain.thork.execution.ExecutionService;
import com.gopaychain.thork.service.ApiExecutionService;
import io.vavr.Tuple2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Random;
import java.net.URI;

public class ApiAction extends Action {


    @Override
    public Mono<Boolean> execute(HashMap<String, Object> results) {
        System.out.println("Executing API action " + super.getId());
        if (true) {
            return  ApiExecutionService.execute(this).flatMap(k -> {
                ExecutionService.getSink().emitNext(new Tuple2<>(this.getId(), k), Sinks.EmitFailureHandler.FAIL_FAST);
                return Mono.empty();
            }).thenReturn(true);


        } else {
            results.put(this.getId(), "Action API Failed ");
            return Mono.just(false);
        }

    }
}
