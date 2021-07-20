package com.gopaychain.thork.service;


import com.gopaychain.thork.model.Decision;
import org.springframework.stereotype.Component;

@Component
public class NodeTraversalService {

    public Decision fetchNode(Decision decision,Integer level, Integer num)
    {
        if(Integer.valueOf(decision.getLevel()).equals(level) &&
                Integer.valueOf(decision.getNum()).equals(num) )
        {
            return decision;

        }
        else if(level - decision.getLevel() > 0  )
        {
            return fetchNode(decision.getDecisions().get(num-1), level,num);
        }

        return null;
    }

}
