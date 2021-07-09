package com.gopaychain.matching.container;

import com.gopaychain.matching.entity.ticket.Intent;
import com.gopaychain.matching.entity.ticket.Ticket;


public abstract class AbstractMatchingContainer {



    public abstract void insertTicket(Ticket ticket);

    public abstract void insertBuyIntent(Intent intent) ;

    public abstract void insertSellIntent(Intent intent);

    public abstract void removeTicket(Ticket ticketId);

    public abstract void removeBuyIntent(Intent intent) ;

    public abstract void removeSellIntent(Intent intent);

    public abstract String runMatches() ;
}
