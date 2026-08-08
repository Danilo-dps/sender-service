package br.com.danilodps.sender.application.service;

import br.com.danilodps.sender.domain.EventRequest;
import br.com.danilodps.sender.infrastructure.client.WebhookDispatcherClient;
import br.com.danilodps.sender.infrastructure.security.WebhookSignatureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

@Slf4j
@Service
@RequiredArgsConstructor
public class WebhookDispatcherService {

    private final WebhookDispatcherClient client;
    private final WebhookSignatureService signatureService;

    @Value("${webhook.secret}")
    private String secret;

    @Async
    @Retryable(
            retryFor = {ResourceAccessException.class},
            backoff = @Backoff(delay = 2000, multiplier = 2)
    )
    public void trigger(EventRequest event) {
        String signature = signatureService.signPayload(event, secret);
        log.info("[Sender] Disparando webhook para evento {}", event.eventId());
        client.send(signature, event.eventId(), event);
    }

}
