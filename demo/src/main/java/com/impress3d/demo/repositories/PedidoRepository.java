package com.impress3d.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.impress3d.demo.model.Cliente;
import com.impress3d.demo.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByCliente(Cliente cliente);
}
