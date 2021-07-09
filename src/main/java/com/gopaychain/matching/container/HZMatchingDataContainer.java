package com.gopaychain.matching.container;

import com.gopaychain.matching.entity.ticket.BuyIntent;
import com.gopaychain.matching.entity.ticket.Intent;
import com.gopaychain.matching.entity.ticket.SellIntent;
import com.gopaychain.matching.entity.ticket.Ticket;
import com.hazelcast.map.IMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.Serializable;

import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class HZMatchingDataContainer extends AbstractMatchingContainer implements Serializable {

    public IMap<Ticket, ConcurrentSkipListSet<BuyIntent>> getBuyerIntents() {
        return buyerIntents;
    }

    public void setBuyerIntents(IMap<Ticket, ConcurrentSkipListSet<BuyIntent>> buyerIntents) {
        this.buyerIntents = buyerIntents;
    }

    public IMap<Ticket, ConcurrentSkipListSet<SellIntent>> getSellerIntents() {
        return sellerIntents;
    }

    public void setSellerIntents(IMap<Ticket, ConcurrentSkipListSet<SellIntent>> sellerIntents) {
        this.sellerIntents = sellerIntents;
    }

    private  IMap<Ticket, ConcurrentSkipListSet<BuyIntent>> buyerIntents;
    private  IMap<Ticket, ConcurrentSkipListSet<SellIntent>> sellerIntents;

    public void insertTicket(Ticket ticket) {
     //   log.info("Inserting ticket");
        buyerIntents.putIfAbsent(ticket, new ConcurrentSkipListSet<>());
        sellerIntents.putIfAbsent(ticket, new ConcurrentSkipListSet<>());

    }

    @Override
    public void insertBuyIntent(Intent intent) {

    }

    @Override
    public void insertSellIntent(Intent intent) {

    }

    public void insertBuyIntent(BuyIntent intent) {
      //  log.info("Inserting Buy Intent");
        IMap<Ticket, ConcurrentSkipListSet<BuyIntent>> last = buyerIntents;

        if (last != null && last.size() != 0 && last.containsKey(intent.getTicket())) {
            ConcurrentSkipListSet<BuyIntent> intents = last.get(intent.getTicket());
            if (intents.size() > 50) {
                log.info("pruning sell Intent tree");
                intents.pollLast();
            }
            intents.add(intent);
            last.replace(intent.getTicket(),intents);
        } else if (last != null && last.size() != 0 && !last.containsKey(intent.getTicket())) {
            this.insertTicket(intent.getTicket());
            this.insertBuyIntent(intent);
        }

    }

    public void insertSellIntent(SellIntent intent) {
   //     log.info("Inserting sell Intent");
        IMap<Ticket, ConcurrentSkipListSet<SellIntent>> last = sellerIntents;

        if (last != null && last.size() != 0 && last.containsKey(intent.getTicket())) {
            ConcurrentSkipListSet<SellIntent> intents = last.get(intent.getTicket());
            if (intents.size() > 50) {
                log.info("Pruning buy Intent tree");
                intents.pollLast();
            }
            intents.add( intent);
            last.replace(intent.getTicket(),intents);
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

    public String runMatches() {
        try {
       //     log.info("Running Match Intent");
            IMap<Ticket, ConcurrentSkipListSet<SellIntent>> sellTicket = sellerIntents;
            IMap<Ticket, ConcurrentSkipListSet<BuyIntent>> buyTicket = buyerIntents;

            if (buyTicket != null && sellTicket != null && buyTicket.size() != 0 && sellTicket.size() != 0) {

                for (Ticket ticket: buyTicket.keySet()) {
                    ConcurrentSkipListSet<BuyIntent> buyTree = buyerIntents.get(ticket);
                    ConcurrentSkipListSet<SellIntent> sellTree = sellerIntents.get(ticket);
                    if (sellTree.size() !=0 &&  buyTree.size() !=0  ) {
                        while (buyTree.first().getUpperPrice() - sellTree.first().getLowerPrice() >= 0) {
                            log.info("Buyer:" + buyTree.pollFirst().toString());
                            log.info("Seller:" + sellTree.pollFirst().toString());
                            sellerIntents.replace(ticket, sellTree);
                            buyerIntents.replace(ticket, buyTree);
                        }
                    }
                }


                return "Found Matches";
            }
            return "no match";
        } catch (Exception e) {
            return "No Match";
        }
    }
}
