package com.impress3d.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.impress3d.demo.model.Cliente;
import com.impress3d.demo.model.SolicitacaoProduto;

public interface SolicitacaoProdutoRepository extends JpaRepository<SolicitacaoProduto, Integer> {
    List<SolicitacaoProduto> findByCliente(Cliente cliente);
}
