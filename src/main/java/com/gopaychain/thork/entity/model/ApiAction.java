package com.gopaychain.thork.entity.model;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Random;
import java.net.URI;
public class ApiAction extends Action {



    @Override
    public boolean execute(HashMap<String,Object> results) {
        System.out.println("Executing API action "+ super.getId());
        if(new Random().nextBoolean())
        {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://httpbin.org/get"))
                    .build();
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenAccept(x->{
                        results.put(this.getId(), x);
                    })
                    .join();

            return true;
        }
        else{
            results.put(this.getId(), "Action API Failed "+ getId());
            return false;
        }

    }
}
