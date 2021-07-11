package com.gopaychain.thork.execution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Component
public class ExecutionResultsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    static Sinks.Many<Object> replaySink = Sinks.many().replay().limit(1);



    public ExecutionResultsService() {
    }
    public Flux<Object> getFlux() {
        return replaySink.asFlux();
    }
    public static Sinks.Many<Object> getSink() {
        return replaySink;
    }
}
