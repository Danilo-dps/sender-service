package br.com.danilodps.sender.infrastructure.client;

import br.com.danilodps.sender.domain.EventRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebhookDispatcherClient {

    private static final String WEBHOOK_URI = "/webhooks/order-event";
    private final RestClient webhookRestClient;

    public void send(String signature, String eventId, EventRequest event) {
        try {
            webhookRestClient.post()
                    .uri(WEBHOOK_URI)
                    .header("X-Webhook-Signature", signature)
                    .header("X-Webhook-Id", eventId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(event)
                    .retrieve()
                    .toBodilessEntity();

            log.info("[Sender] Webhook entregue com sucesso: {}", eventId);
//            throw new ResourceAccessException("Erro simulado");
        } catch (ResourceAccessException e) {
            log.error("[Sender] Falha de conexão ao send webhook {}: {}", eventId, e.getMessage());
            throw e; // propaga para o @Retryable atuar
        }
    }

}
