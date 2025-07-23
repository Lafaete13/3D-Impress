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
import com.impress3d.demo.model.SolicitacaoProduto;
import com.impress3d.demo.services.SolicitacaoProdutoService;

@RestController
@RequestMapping("/api/solicitacoes")
public class SolicitacaoProdutoController {

    @Autowired
    private SolicitacaoProdutoService solicitacaoProdutoService;

    // RF06: Cliente envia solicitação de produto sob encomenda
    @PostMapping
    public ResponseEntity<SolicitacaoProduto> createSolicitacao(@AuthenticationPrincipal Cliente clienteAutenticado, @RequestBody SolicitacaoProduto solicitacao) {
        SolicitacaoProduto createdSolicitacao = solicitacaoProdutoService.createSolicitacao(solicitacao, clienteAutenticado);
        return ResponseEntity.ok(createdSolicitacao);
    }

    // RF08: Cliente acompanha status da sua solicitação
    @GetMapping("/meus")
    public ResponseEntity<List<SolicitacaoProduto>> getMinhasSolicitacoes(@AuthenticationPrincipal Cliente clienteAutenticado) {
        List<SolicitacaoProduto> solicitacoes = solicitacaoProdutoService.getSolicitacoesByCliente(clienteAutenticado);
        return ResponseEntity.ok(solicitacoes);
    }

    // RF07: Administrador visualiza e altera o status da solicitação
    @GetMapping("/admin")
    public ResponseEntity<List<SolicitacaoProduto>> getAllSolicitacoes() {
        return ResponseEntity.ok(solicitacaoProdutoService.getAllSolicitacoes());
    }

    // RF07: Administrador altera o status da solicitação
    @PutMapping("/admin/{id}/status")
    public ResponseEntity<SolicitacaoProduto> updateStatusSolicitacao(@PathVariable Integer id, @RequestBody Map<String, String> request) {
        String newStatus = request.get("status");
        SolicitacaoProduto updatedSolicitacao = solicitacaoProdutoService.updateStatusSolicitacao(id, newStatus);
        return ResponseEntity.ok(updatedSolicitacao);
    }
}
