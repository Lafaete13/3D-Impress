package br.com.impress3d.ecommerce.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.impress3d.ecommerce.model.CarrinhoCompras;

public interface CarrinhoComprasRepository extends JpaRepository<CarrinhoCompras, Long> {
    Optional<CarrinhoCompras> findByClienteIdCliente(Long idCliente);
}