package com.gopaychain.thork.service;

import com.gopaychain.thork.model.Action;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ApiExecutionService {


    private static final WebClient httpBinClient = WebClient.create("https://httpbin.org/get");


    public static Mono<Object> execute(Action action) {
       return httpBinClient.get()
                .exchangeToMono(response -> {
                            if (response.statusCode().is2xxSuccessful()) {
                                return response.bodyToMono(String.class);
                            } else {
                                return Mono.just("Failed");
                            }
                        }
                );

    }

}
