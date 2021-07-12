package com.gopaychain.thork.model;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class CallBackAction extends Action  {

    @Override
    public boolean execute(HashMap<String, Object> results) throws ExecutionException, InterruptedException {
        System.out.println("Executing failure callback action " +this.getId());
        return false;
    }
}
