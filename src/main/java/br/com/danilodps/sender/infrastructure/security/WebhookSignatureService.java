package br.com.danilodps.sender.infrastructure.security;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class WebhookSignatureService {

    private final ObjectMapper objectMapper;
    private static final String HMAC_ALGO = "HmacSHA256";

    @SneakyThrows
    public String signPayload(Object payload, String secret) {
        Mac mac = Mac.getInstance(HMAC_ALGO);
        SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), HMAC_ALGO);
        mac.init(secretKey);

        byte[] bytes = objectMapper.writeValueAsBytes(payload);
        byte[] signature = mac.doFinal(bytes);

        return Base64.getEncoder().encodeToString(signature);
    }

}
