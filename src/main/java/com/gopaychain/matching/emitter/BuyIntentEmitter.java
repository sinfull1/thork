package com.gopaychain.matching.emitter;

import com.gopaychain.matching.entity.ticket.Intent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

@Component
public class BuyIntentEmitter {

    Sinks.Many<Object> buyIntentSink = Sinks.many().replay().limit(1);

    public void publishBuyIntent(Intent ticket) {
        buyIntentSink.emitNext(ticket, Sinks.EmitFailureHandler.FAIL_FAST);
    }
}