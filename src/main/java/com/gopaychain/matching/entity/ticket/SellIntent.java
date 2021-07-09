package com.gopaychain.matching.entity.ticket;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Comparator;

@Data
@NoArgsConstructor
public class SellIntent extends Intent implements  Comparable<Intent>, Serializable {
    public SellIntent(Long intentId, Ticket ticket, Double lowerPrice, Double upperPrice, String type) {
        super(intentId, ticket, lowerPrice, upperPrice, type);
    }

    @Override
    public int compareTo( Intent o2) {
        return this.getUpperPrice().compareTo(o2.getUpperPrice());
    }
    @Override
    public String toString() {
        return super.toString();
    }

}
