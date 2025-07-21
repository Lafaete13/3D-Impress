package br.com.impress3d.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.impress3d.ecommerce.dtos.AtualizarStatusDTO;
import br.com.impress3d.ecommerce.services.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
	
	@Autowired
    private PedidoService pedidoService;

    // RF15: Permitir que o administrador visualize e atualize o status dos pedidos
    @PatchMapping("/pedidos/{pedidoId}/status")
    public ResponseEntity<Void> atualizarStatusPedido(@PathVariable Long pedidoId, @RequestBody @Valid AtualizarStatusDTO dto) {
        pedidoService.atualizarStatus(pedidoId, dto.status());
        return ResponseEntity.ok().build();
    }

}
