package com.gopaychain.matching.entity.ticket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Intent  implements Serializable{
    private Long intentId;
    private Ticket ticket;
    private Double lowerPrice;
    private Double upperPrice;
    private String type;


}
