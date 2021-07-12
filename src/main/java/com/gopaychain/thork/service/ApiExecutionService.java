package com.gopaychain.thork.service;


import com.gopaychain.thork.model.Action;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutionException;

@Component
public class ApiExecutionService {


    private static final WebClient httpBinClient = WebClient.create("https://httpbin.org/get");


    public static Mono<Object> nonBlockingExecute(Action action) {
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

    public static String execute(Action action) throws ExecutionException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://httpbin.org/get"))
                .build();
        String result = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body).get();
        return result;

    }





}