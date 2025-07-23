package com.impress3d.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.impress3d.demo.model.CarrinhoCompras;
import com.impress3d.demo.model.ItemCarrinho;
import com.impress3d.demo.model.Produto;

public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinho, Integer> {
	Optional<ItemCarrinho> findByCarrinhoAndProduto(CarrinhoCompras carrinho, Produto produto);
}
