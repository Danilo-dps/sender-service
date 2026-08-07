package br.com.danilodps.sender.domain;

import java.math.BigDecimal;
import java.time.Instant;

public record EventRequest (String eventId,
                           String orderId,
                           String status,
                           BigDecimal price,
                           Instant timestamp) {
}
