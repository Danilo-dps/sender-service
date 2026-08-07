package br.com.danilodps.sender.application.service;

import br.com.danilodps.sender.domain.EventRequest;
import br.com.danilodps.sender.domain.enums.EventStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final WebhookDispatcherService dispatcher;

    public String processOrder(String orderId, BigDecimal price) {
        var event = new EventRequest(UUID.randomUUID().toString(), orderId, EventStatus.PAGO.name(), price, Instant.now());

        dispatcher.trigger(event);

        return "Pedido processado. Evento " + event.eventId() + " enfileirado para envio.";
    }
}
