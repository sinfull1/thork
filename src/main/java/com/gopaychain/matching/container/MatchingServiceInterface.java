package com.gopaychain.matching.container;

import org.springframework.boot.context.event.ApplicationStartingEvent;

import java.util.HashMap;

public interface MatchingServiceInterface {

    // This method would be pick up  the last snapshot state saved in DB

    public void initializeStateFromDb(AbstractMatchingContainer container);

    // This method would be persist on shutdown the last snapshot state saved in DB or adhoc


    public void persistStateInDb(AbstractMatchingContainer container);

    // This method would be just start the matching engine

    public void runMatchingEngine(AbstractMatchingContainer container);

    public void stopMatchingEngine();

}
