package com.gopaychain.matching.entity.ticket;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Comparator;

@Data
@NoArgsConstructor
public class BuyIntent extends Intent implements Serializable, Comparable<Intent> {


    @Override
    public String toString() {
        return super.toString();
    }

    public BuyIntent(Long intentId, Ticket ticket, Double lowerPrice, Double upperPrice, String type) {
        super(intentId, ticket, lowerPrice, upperPrice, type);
    }

    @Override
    public int compareTo(Intent o2) {
        return o2.getLowerPrice().compareTo(this.getLowerPrice());
    }
}
