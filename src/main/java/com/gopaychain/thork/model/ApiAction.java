package com.gopaychain.thork.model;

import com.gopaychain.thork.model.Action;
import com.gopaychain.thork.service.ApiExecutionService;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Random;
import java.net.URI;
import java.util.concurrent.ExecutionException;

public class ApiAction extends Action {

    @Override
    public boolean execute(HashMap<String,Object> results) throws ExecutionException, InterruptedException {
        System.out.println("Executing API action "+ super.getId());
        if(!(Math.random()*100>75))
        {
            results.put(this.getId(), ApiExecutionService.execute(this));
            return true;
        }
        else{
            results.put(this.getId(), "Action API Failed "+ getId());
            return false;
        }
    }
}
