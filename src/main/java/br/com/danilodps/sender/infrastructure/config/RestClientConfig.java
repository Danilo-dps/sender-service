package br.com.danilodps.sender.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Value("${webhook.receiver.url}")
    private String receiverUrl;

    @Bean
    public RestClient webhookRestClient() {
        return RestClient.builder()
                .baseUrl(receiverUrl)
                .build();
    }

}