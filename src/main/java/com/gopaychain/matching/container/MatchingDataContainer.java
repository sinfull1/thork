package com.gopaychain.matching.container;


import com.gopaychain.matching.entity.ticket.Intent;
import com.gopaychain.matching.entity.ticket.Ticket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListSet;

@Component
@Slf4j
public class MatchingDataContainer extends AbstractMatchingContainer {

    @Override
    public String toString() {
        return "MatchingDataContainer{" +
                "buyerIntents=" + buyerIntents.toString() +
                ", sellerIntents=" + sellerIntents.toString() +
                '}';
    }

    public ConcurrentLinkedQueue<ConcurrentHashMap<Ticket, ConcurrentSkipListSet<Intent>>> buyerIntents = new ConcurrentLinkedQueue<>();
    public ConcurrentLinkedQueue<ConcurrentHashMap<Ticket, ConcurrentSkipListSet<Intent>>> sellerIntents = new ConcurrentLinkedQueue<>();
    public Comparator<Intent> buyIntents = (o1, o2) -> o2.getUpperPrice().compareTo(o1.getUpperPrice());
    public Comparator<Intent> sellIntents = Comparator.comparing(Intent::getLowerPrice);


    public  void insertTicket(Ticket ticket) {
        log.info("Inserting ticket");
        if (buyerIntents.size() == 0) {
            ConcurrentHashMap<Ticket, ConcurrentSkipListSet<Intent>> lastEntryMap = new ConcurrentHashMap<>();
            lastEntryMap.put(ticket, new ConcurrentSkipListSet<>(buyIntents));
            buyerIntents.offer(lastEntryMap);
        }
        if (buyerIntents.size() != 0 && !buyerIntents.peek().containsKey(ticket)) {
            ConcurrentHashMap<Ticket, ConcurrentSkipListSet<Intent>> lastEntryMap = buyerIntents.peek();
            lastEntryMap.put(ticket, new ConcurrentSkipListSet<>(buyIntents));
            buyerIntents.offer(lastEntryMap);
        }
        if (sellerIntents.size() == 0) {
            ConcurrentHashMap<Ticket, ConcurrentSkipListSet<Intent>> lastEntryMap = new ConcurrentHashMap<>();
            lastEntryMap.put(ticket, new ConcurrentSkipListSet<>(sellIntents));
            sellerIntents.offer(lastEntryMap);
        }

        if (sellerIntents.size() != 0 && !sellerIntents.peek().containsKey(ticket)) {
            ConcurrentHashMap<Ticket, ConcurrentSkipListSet<Intent>> lastEntryMap = sellerIntents.peek();
            lastEntryMap.put(ticket, new ConcurrentSkipListSet<>(sellIntents));
            sellerIntents.offer(lastEntryMap);
        }

    }

    public  void insertBuyIntent(Intent intent) {
        log.info("Inserting Buy Intent");
        ConcurrentHashMap<Ticket, ConcurrentSkipListSet<Intent>> last = buyerIntents.peek();

        if (last != null && last.size() != 0 && last.containsKey(intent.getTicket())) {
            ConcurrentSkipListSet<Intent> intents = last.get(intent.getTicket());
            if (intents.size()>50)
            {
                log.info("pruning sell Intent");
                intents.pollLast();
            }
            intents.add(intent);
        } else if (last != null && last.size() != 0 && !last.containsKey(intent.getTicket())) {
            this.insertTicket(intent.getTicket());
            this.insertBuyIntent(intent);
        }

    }

    public  void insertSellIntent(Intent intent) {
        log.info("Inserting sell Intent");
        ConcurrentHashMap<Ticket, ConcurrentSkipListSet<Intent>> last = sellerIntents.peek();

        if (last != null && last.size() != 0 && last.containsKey(intent.getTicket())) {
            ConcurrentSkipListSet<Intent> intents = last.get(intent.getTicket());
            if (intents.size()>50)
            {
                log.info("pruning buy  Intent");
                intents.pollLast();
            }
            intents.add(intent);
        } else if (last != null && last.size() != 0 && !last.containsKey(intent.getTicket())) {
            this.insertTicket(intent.getTicket());
            this.insertSellIntent(intent);
        }

    }

    @Override
    public void removeTicket(Ticket ticketId) {

    }

    @Override
    public void removeBuyIntent(Intent intent) {

    }

    @Override
    public void removeSellIntent(Intent intent) {

    }

    public  String runMatches() {
        try {
            log.info("Running Match Intent");
            ConcurrentHashMap<Ticket, ConcurrentSkipListSet<Intent>> sellTicket = sellerIntents.peek();
            ConcurrentHashMap<Ticket, ConcurrentSkipListSet<Intent>> buyTicket = buyerIntents.peek();

            if (buyTicket != null && sellTicket != null && buyTicket.size() != 0 && sellTicket.size() != 0) {
                buyTicket.entrySet().stream().forEach(buy -> {
                    while (buy.getValue().first().getUpperPrice() - sellTicket.get(buy.getKey()).first().getLowerPrice() >= 0) {
                        log.info("Buyer:" + buy.getValue().pollFirst().toString());
                        log.info("Seller:" +sellTicket.get(buy.getKey()).pollFirst().toString());
                    }
                });


                return "Found Matches";
            }
            if (sellerIntents.size()>10 || buyerIntents.size()>10) {
                log.info("Pruning the Linked Queue");
                sellerIntents.poll();
                buyerIntents.poll();
            }
          return "no match";
        } catch (Exception e) {
            return "No Match";
        }
    }
}
