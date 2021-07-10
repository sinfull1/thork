package com.gopaychain.thork.execution;


import com.gopaychain.thork.container.OrchestrationExecutor;
import com.gopaychain.thork.model.Decision;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class ExecutionEngine {

    @Autowired
    ExecutionService executionService;

    public void queueExecution(Mono<Decision> decision) {
        decision.flatMap(dec -> Mono.just(OrchestrationExecutor.execute(dec))).subscribe(res -> {
            res.entrySet().forEach(x->{log.info(x.toString());});
        });
    }


}
