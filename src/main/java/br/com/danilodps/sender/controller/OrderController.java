package br.com.danilodps.sender.controller;

import br.com.danilodps.sender.application.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/{orderId}/pay")
    public ResponseEntity<String> pay(
            @PathVariable String orderId,
            @RequestParam BigDecimal price) {

        String result = orderService.processOrder(orderId, price);
        return ResponseEntity.accepted().body(result);
    }

}
