package com.impress3d.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.impress3d.demo.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    List<Produto> findByCategoria(String categoria);
}
