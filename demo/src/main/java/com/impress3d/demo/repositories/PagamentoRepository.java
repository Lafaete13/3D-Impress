package com.impress3d.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.impress3d.demo.model.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
}