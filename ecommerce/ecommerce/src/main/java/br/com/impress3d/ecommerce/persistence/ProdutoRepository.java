package br.com.impress3d.ecommerce.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.impress3d.ecommerce.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}