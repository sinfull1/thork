package com.gopaychain.matching;

import com.gopaychain.matching.container.MatchingDataContainer;
import com.gopaychain.matching.entity.ticket.Intent;
import com.gopaychain.matching.entity.ticket.Ticket;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestMatchingEngine {


    MatchingDataContainer matchingDataContainer = new MatchingDataContainer();

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
                System.out.println(e.getCause());
            }
        }
    }


    public void addBuyIntent() {
        while (true) {
            try {
                Thread.sleep(5);
                Ticket ticket = tickets.get(random.nextInt(tickets.size()));
                Intent intent = new Intent();
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
                Thread.sleep(5);
                Ticket ticket = tickets.get(random.nextInt(tickets.size()));
                Intent intent = new Intent();
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
        executorService.submit(this::addTicket);
        executorService.submit(this::addBuyIntent);
        executorService.submit(this::addSellIntent);

        while (true) {
            Thread.sleep(50);
            matchingDataContainer.runMatches();
        }

    }
}
