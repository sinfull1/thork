package com.gopaychain.matching.container;

import java.util.Map;

public interface MatchingAppInterface {

    // Daemon for getting Intents as per configuration (long polling, push events, post etc)
    public void startBuyIntentsDaemon(Map<String,String> configMap);
    public void startSellIntentsDaemon(Map<String,String> configMap);

    // shutdown Daemon getting Intents
    public void shutDownBuyIntentsDaemon(Map<String,String> configMap);
    public void shutDownSellIntentsDaemon(Map<String,String> configMap);

    // Daemon for getting tickets
    public void startTicketDaemon(Map<String,String> configMap);
    public void shutDownTicketDaemon(Map<String,String> configMap);


    // Daemon for matching service
    public void startMatchingService(Map<String,String> configMap);
    public void stopMatchingService(Map<String,String> configMap);


}
