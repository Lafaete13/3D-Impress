package com.impress3d.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impress3d.demo.exceptions.ResourceNotFoundException;
import com.impress3d.demo.model.Administrador;
import com.impress3d.demo.model.Produto;
import com.impress3d.demo.repositories.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto createProduto(Produto produto, Administrador administrador) {
        produto.setAdministrador(administrador);
        return produtoRepository.save(produto);
    }

    public List<Produto> getAllProdutos() {
        return produtoRepository.findAll();
    }

    public Produto getProdutoById(Integer id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com ID: " + id));
    }

    public Produto updateProduto(Integer id, Produto produtoDetails) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com ID: " + id));

        produto.setNome(produtoDetails.getNome());
        produto.setPreco(produtoDetails.getPreco());
        produto.setValorEst(produtoDetails.getValorEst());
        produto.setCategoria(produtoDetails.getCategoria());
        return produtoRepository.save(produto);
    }

    public void deleteProduto(Integer id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com ID: " + id));
        produtoRepository.delete(produto);
    }

    public List<Produto> getProdutosByCategoria(String categoria) {
        return produtoRepository.findByCategoria(categoria);
    }
}
