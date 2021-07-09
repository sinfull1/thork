package com.gopaychain.matching;

import com.gopaychain.matching.container.HZMatchingDataContainer;
import com.gopaychain.matching.entity.ticket.BuyIntent;
import com.gopaychain.matching.entity.ticket.SellIntent;
import com.gopaychain.matching.entity.ticket.Ticket;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestHazelcastMatchingEngine implements Serializable {


    HZMatchingDataContainer matchingDataContainer;

    public Long ticketGen = 0L;
    public Long intentGen = 0L;
    public Random random = new Random();
    public List<Ticket> tickets = new ArrayList<Ticket>();

    public void addTicket() {
        while (true) {
            try {
                Thread.sleep(500);
                Ticket ticket = new Ticket();
                ticket.setTicketId(Long.valueOf(ticketGen++));
                if (ticketGen % 3 == 0) {
                    ticket.setTicketType("SELL");
                } else {
                    ticket.setTicketType("BUY");
                }
                ticket.setBasePrice(random.nextDouble() * 100);
                tickets.add(ticket);
                matchingDataContainer.insertTicket(ticket);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void addBuyIntent() {
        while (true) {
            try {

                Ticket ticket = tickets.get(random.nextInt(tickets.size()));
                BuyIntent intent = new BuyIntent();
                intent.setIntentId(intentGen++);
                intent.setTicket(ticket);
                double range = random.nextDouble() * 10;
                double range1 = random.nextDouble();
                intent.setLowerPrice(ticket.getBasePrice() - range);
                intent.setUpperPrice(ticket.getBasePrice() - 0.5 + range1);
                matchingDataContainer.insertBuyIntent(intent);
            } catch (Exception e) {
                System.out.println(e.getCause());
            }
        }
    }


    public void addSellIntent() {
        while (true) {
            try {

                Ticket ticket = tickets.get(random.nextInt(tickets.size()));
                SellIntent intent = new SellIntent();
                intent.setIntentId(intentGen++);
                intent.setTicket(ticket);
                double range = random.nextDouble();
                double range1 = random.nextDouble() * 10;
                intent.setLowerPrice(ticket.getBasePrice() + 0.45 - range);
                intent.setUpperPrice(ticket.getBasePrice() + range1);
                matchingDataContainer.insertSellIntent(intent);
            } catch (Exception e) {
                System.out.println(e.getCause());
            }
        }
    }

    @Test
    public void test() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(8);

        // Start the client and connect to the cluster

        // run a localhost imdg
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.getNetworkConfig().addAddress("192.168.1.27:5701");
        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        IMap<Ticket, ConcurrentSkipListSet<BuyIntent>> buyIntents = client.getMap("buy-Intents"); //creates the map proxy
        IMap<Ticket, ConcurrentSkipListSet<SellIntent>> sellIntents = client.getMap("sell-Intents");


        matchingDataContainer = new HZMatchingDataContainer();
        matchingDataContainer.setBuyerIntents(buyIntents);
        matchingDataContainer.setSellerIntents(sellIntents);

        executorService.submit(this::addTicket);
        executorService.submit(this::addBuyIntent);
        executorService.submit(this::addSellIntent);

        while (true) {
            Thread.sleep(500);
            matchingDataContainer.runMatches();
        }

    }
}
