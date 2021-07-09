package com.gopaychain.matching.emitter;


import com.gopaychain.matching.entity.ticket.Ticket;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

@Component
public class TicketEmitter {


    Sinks.Many<Object> ticketSink = Sinks.many().replay().limit(1);

    public void publishTicket(Ticket ticket){
        ticketSink.emitNext(ticket, Sinks.EmitFailureHandler.FAIL_FAST);
    }

}
