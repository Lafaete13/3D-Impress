package br.com.impress3d.ecommerce.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.impress3d.ecommerce.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}