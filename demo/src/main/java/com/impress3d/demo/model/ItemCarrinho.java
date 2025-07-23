package com.impress3d.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemCarrinho {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // ID próprio para ItemCarrinho

	@ManyToOne
	@JoinColumn(name = "idCarr")
	private CarrinhoCompras carrinho;

	@ManyToOne
	@JoinColumn(name = "idProduto")
	private Produto produto;

	private Integer quantidade;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CarrinhoCompras getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(CarrinhoCompras carrinho) {
		this.carrinho = carrinho;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

}
