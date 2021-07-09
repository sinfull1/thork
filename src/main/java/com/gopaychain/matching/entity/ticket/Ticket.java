package com.gopaychain.matching.entity.ticket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket implements Serializable {

    private Long ticketId;
    private String ticketType;
    private String userId;
    private Double basePrice;
}
