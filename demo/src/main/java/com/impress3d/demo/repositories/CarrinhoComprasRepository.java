package com.impress3d.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.impress3d.demo.model.CarrinhoCompras;
import com.impress3d.demo.model.Cliente;

public interface CarrinhoComprasRepository extends JpaRepository<CarrinhoCompras, Integer> {
	Optional<CarrinhoCompras> findByCliente(Cliente cliente);
}
