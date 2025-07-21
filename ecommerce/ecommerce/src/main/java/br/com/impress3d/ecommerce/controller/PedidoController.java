package br.com.impress3d.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.impress3d.ecommerce.dtos.FinalizarPedidoDTO;
import br.com.impress3d.ecommerce.services.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {
	
	@Autowired
    private PedidoService pedidoService;

    // RF13 & RF14: Finalizar a compra
    @PostMapping("/finalizar")
    public ResponseEntity<Void> finalizarPedido(@RequestBody @Valid FinalizarPedidoDTO dto, @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(pedidoService.finalizarPedido(userDetails.getUsername(), dto));
      //  return ResponseEntity.status(201).build(); // Placeholder para o DTO do pedido criado
    }

    // Endpoint para o usuário listar seus próprios pedidos
    @GetMapping("/meus-pedidos")
    public ResponseEntity<List<Void>> listarMeusPedidos(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(pedidoService.listarPedidosPorCliente(userDetails.getUsername()));
      //  return ResponseEntity.ok().build(); // Placeholder
    }
}
