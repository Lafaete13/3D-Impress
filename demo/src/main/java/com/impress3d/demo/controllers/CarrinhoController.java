package com.impress3d.demo.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.impress3d.demo.model.CarrinhoCompras;
import com.impress3d.demo.model.Cliente;
import com.impress3d.demo.services.CarrinhoService;

@RestController
@RequestMapping("/api/carrinho")
public class CarrinhoController {

    @Autowired
    private CarrinhoService carrinhoService;

    // RF09: Adicionar produtos ao carrinho
    @PostMapping("/adicionar")
    public ResponseEntity<CarrinhoCompras> adicionarProdutoAoCarrinho(@AuthenticationPrincipal Cliente clienteAutenticado, @RequestBody Map<String, Integer> request) {
        Integer idProduto = request.get("idProduto");
        Integer quantidade = request.get("quantidade");
        CarrinhoCompras carrinho = carrinhoService.adicionarProduto(clienteAutenticado, idProduto, quantidade);
        return ResponseEntity.ok(carrinho);
    }

    // RF10: Remover produtos do carrinho (total ou parcialmente)
    @PostMapping("/remover")
    public ResponseEntity<CarrinhoCompras> removerProdutoDoCarrinho(@AuthenticationPrincipal Cliente clienteAutenticado, @RequestBody Map<String, Integer> request) {
        Integer idProduto = request.get("idProduto");
        Integer quantidade = request.get("quantidade"); // Pode ser null para remover totalmente
        CarrinhoCompras carrinho = carrinhoService.removerProduto(clienteAutenticado, idProduto, quantidade);
        return ResponseEntity.ok(carrinho);
    }

    // RF11: Exibir o subtotal dos itens no carrinho
    @GetMapping("/subtotal")
    public ResponseEntity<Double> getSubtotalCarrinho(@AuthenticationPrincipal Cliente clienteAutenticado) {
        Double subtotal = carrinhoService.calcularSubtotal(clienteAutenticado);
        return ResponseEntity.ok(subtotal);
    }

    // RF12: Obter carrinho (persistente)
    @GetMapping
    public ResponseEntity<CarrinhoCompras> getCarrinho(@AuthenticationPrincipal Cliente clienteAutenticado) {
        CarrinhoCompras carrinho = carrinhoService.getCarrinhoByCliente(clienteAutenticado);
        return ResponseEntity.ok(carrinho);
    }

    // Limpar todo o carrinho
    @DeleteMapping("/limpar")
    public ResponseEntity<Void> limparCarrinho(@AuthenticationPrincipal Cliente clienteAutenticado) {
        carrinhoService.limparCarrinho(clienteAutenticado);
        return ResponseEntity.noContent().build();
    }
}
