package com.gopaychain.matching.emitter;

import com.gopaychain.matching.entity.ticket.Intent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

@Component
public class SellIntentEmitter {
    Sinks.Many<Object> sellIntentSink = Sinks.many().replay().limit(1);

    public void publishSellIntent(Intent ticket){
        sellIntentSink.emitNext(ticket, Sinks.EmitFailureHandler.FAIL_FAST);
    }
}
