package com.impress3d.demo.services;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impress3d.demo.exceptions.ResourceNotFoundException;
import com.impress3d.demo.model.Cliente;
import com.impress3d.demo.model.SolicitacaoProduto;
import com.impress3d.demo.repositories.SolicitacaoProdutoRepository;

@Service
public class SolicitacaoProdutoService {

    @Autowired
    private SolicitacaoProdutoRepository solicitacaoProdutoRepository;

    public SolicitacaoProduto createSolicitacao(SolicitacaoProduto solicitacao, Cliente cliente) {
        solicitacao.setCliente(cliente);
        solicitacao.setDataSolicitacao(LocalDateTime.now());
        solicitacao.setStatus("Pendente"); // Status inicial
        return solicitacaoProdutoRepository.save(solicitacao);
    }

    public List<SolicitacaoProduto> getSolicitacoesByCliente(Cliente cliente) {
        return solicitacaoProdutoRepository.findByCliente(cliente);
    }

    public SolicitacaoProduto getSolicitacaoById(Integer idSolicitacao) {
        return solicitacaoProdutoRepository.findById(idSolicitacao)
                .orElseThrow(() -> new ResourceNotFoundException("Solicitação de produto não encontrada com ID: " + idSolicitacao));
    }

    public SolicitacaoProduto updateStatusSolicitacao(Integer idSolicitacao, String newStatus) {
        SolicitacaoProduto solicitacao = solicitacaoProdutoRepository.findById(idSolicitacao)
                .orElseThrow(() -> new ResourceNotFoundException("Solicitação de produto não encontrada com ID: " + idSolicitacao));

        // Validações de status (RF07, RF08)
        List<String> validStatuses = Arrays.asList("Pendente", "Aprovado", "Rejeitado", "Em Processamento", "Enviado", "Entregue", "Cancelado");
        if (!validStatuses.contains(newStatus)) {
            throw new IllegalArgumentException("Status inválido: " + newStatus);
        }

        solicitacao.setStatus(newStatus);
        return solicitacaoProdutoRepository.save(solicitacao);
    }

    public List<SolicitacaoProduto> getAllSolicitacoes() {
        return solicitacaoProdutoRepository.findAll();
    }
}
