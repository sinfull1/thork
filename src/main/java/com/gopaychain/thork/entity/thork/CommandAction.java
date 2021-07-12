package com.gopaychain.thork.entity.thork;

import java.util.HashMap;
import java.util.Random;

public class CommandAction extends Action{
    @Override
    public boolean execute(HashMap<String,Object> results) {
        System.out.println("Executing Command action "+ super.getId());
        if(new Random().nextBoolean())
        {
            results.put(getId(),"Action Command Success "+ super.getId());
            return true;
        }
        else{
            results.put(getId(),"Action Command Failed "+ getId());
            return false;
        }

    }
}
