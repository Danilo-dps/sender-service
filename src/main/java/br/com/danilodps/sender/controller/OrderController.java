package br.com.danilodps.sender.controller;

import br.com.danilodps.sender.application.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/pedidos")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/{pedidoId}/pagar")
    public ResponseEntity<String> pay(
            @PathVariable String pedidoId,
            @RequestParam(defaultValue = "199.90") BigDecimal valor) {

        String resultado = orderService.processOrder(pedidoId, valor);
        return ResponseEntity.accepted().body(resultado);
    }

}
