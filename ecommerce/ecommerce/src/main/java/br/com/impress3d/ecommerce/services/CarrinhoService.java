package br.com.impress3d.ecommerce.services;

import br.com.impress3d.ecommerce.dtos.ItemCarrinhoDTO;
import br.com.impress3d.ecommerce.model.CarrinhoCompras;
import jakarta.validation.Valid;

public class CarrinhoService {

	public void adicionarItem(String username, @Valid ItemCarrinhoDTO itemDTO) {
		// TODO Auto-generated method stub
		
	}

	public void removerItem(String username, Long produtoId, int quantidade) {
		// TODO Auto-generated method stub
		
	}

	public CarrinhoCompras verCarrinho(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
