package br.com.impress3d.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.impress3d.ecommerce.dtos.ProdutoDTO;
import br.com.impress3d.ecommerce.persistence.ProdutoRepository;
import jakarta.validation.Valid;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public ProdutoDTO criarProduto(@Valid ProdutoDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ProdutoDTO> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	public ProdutoDTO buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ProdutoDTO atualizarProduto(Long id, @Valid ProdutoDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deletarProduto(Long id) {
		// TODO Auto-generated method stub
		
	}

}
