package com.impress3d.demo.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.impress3d.demo.model.Cliente;
import com.impress3d.demo.model.Pedido;
import com.impress3d.demo.services.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // RF13, RF14: Finalizar compra
    @PostMapping("/finalizar")
    public ResponseEntity<Pedido> finalizarCompra(@AuthenticationPrincipal Cliente clienteAutenticado, @RequestBody Map<String, String> request) {
        String metodoPagamento = request.get("metodoPagamento");
        String enderecoEntrega = request.get("enderecoEntrega"); // Pode ser null, se for para usar o endereço cadastrado
        Pedido pedido = pedidoService.finalizarPedido(clienteAutenticado, metodoPagamento, enderecoEntrega);
        return ResponseEntity.ok(pedido);
    }

    // RF08: Acompanhar status da solicitação (Pedidos do cliente)
    @GetMapping("/meus")
    public ResponseEntity<List<Pedido>> getMeusPedidos(@AuthenticationPrincipal Cliente clienteAutenticado) {
        List<Pedido> pedidos = pedidoService.getPedidosByCliente(clienteAutenticado);
        return ResponseEntity.ok(pedidos);
    }

    // RF08: Acompanhar status de um pedido específico
    @GetMapping("/meus/{id}")
    public ResponseEntity<Pedido> getMeuPedidoById(@AuthenticationPrincipal Cliente clienteAutenticado, @PathVariable Integer id) {
        Pedido pedido = pedidoService.getPedidoById(id);
        if (!pedido.getCliente().getIdCliente().equals(clienteAutenticado.getIdCliente())) {
            return ResponseEntity.status(403).build(); // Acesso negado
        }
        return ResponseEntity.ok(pedido);
    }

    // RF15: Administrador visualiza todos os pedidos
    @GetMapping("/admin")
    public ResponseEntity<List<Pedido>> getAllPedidos() {
        return ResponseEntity.ok(pedidoService.getAllPedidos());
    }

    // RF15: Administrador atualiza status do pedido
    @PutMapping("/admin/{id}/status")
    public ResponseEntity<Pedido> updateStatusPedido(@PathVariable Integer id, @RequestBody Map<String, String> request) {
        String newStatus = request.get("status");
        Pedido updatedPedido = pedidoService.updateStatusPedido(id, newStatus);
        return ResponseEntity.ok(updatedPedido);
    }
}
