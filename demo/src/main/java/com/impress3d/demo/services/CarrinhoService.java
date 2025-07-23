package com.impress3d.demo.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impress3d.demo.exceptions.ResourceNotFoundException;
import com.impress3d.demo.model.CarrinhoCompras;
import com.impress3d.demo.model.Cliente;
import com.impress3d.demo.model.ItemCarrinho;
import com.impress3d.demo.model.Produto;
import com.impress3d.demo.repositories.CarrinhoComprasRepository;
import com.impress3d.demo.repositories.ItemCarrinhoRepository;
import com.impress3d.demo.repositories.ProdutoRepository;

@Service
public class CarrinhoService {

    @Autowired
    private CarrinhoComprasRepository carrinhoComprasRepository;

    @Autowired
    private ItemCarrinhoRepository itemCarrinhoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public CarrinhoCompras getOrCreateCarrinho(Cliente cliente) {
        return carrinhoComprasRepository.findByCliente(cliente)
                .orElseGet(() -> {
                    CarrinhoCompras novoCarrinho = new CarrinhoCompras();
                    novoCarrinho.setCliente(cliente);
                    novoCarrinho.setDataCriacao(LocalDateTime.now());
                    return carrinhoComprasRepository.save(novoCarrinho);
                });
    }

    public CarrinhoCompras adicionarProduto(Cliente cliente, Integer idProduto, Integer quantidade) {
        CarrinhoCompras carrinho = getOrCreateCarrinho(cliente);
        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com ID: " + idProduto));

        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }
        if (produto.getValorEst() != null && produto.getValorEst() < quantidade) {
            throw new IllegalArgumentException("Estoque insuficiente para o produto: " + produto.getNome());
        }

        Optional<ItemCarrinho> existingItemOptional = itemCarrinhoRepository.findByCarrinhoAndProduto(carrinho, produto);

        if (existingItemOptional.isPresent()) {
            ItemCarrinho item = existingItemOptional.get();
            item.setQuantidade(item.getQuantidade() + quantidade);
            itemCarrinhoRepository.save(item);
        } else {
            ItemCarrinho newItem = new ItemCarrinho();
            newItem.setCarrinho(carrinho);
            newItem.setProduto(produto);
            newItem.setQuantidade(quantidade);
            itemCarrinhoRepository.save(newItem);
            carrinho.getItens().add(newItem); // Adiciona ao carrinho em memória
        }
        return carrinho;
    }

    public CarrinhoCompras removerProduto(Cliente cliente, Integer idProduto, Integer quantidade) {
        CarrinhoCompras carrinho = carrinhoComprasRepository.findByCliente(cliente)
                .orElseThrow(() -> new ResourceNotFoundException("Carrinho não encontrado para o cliente."));
        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com ID: " + idProduto));

        Optional<ItemCarrinho> existingItemOptional = itemCarrinhoRepository.findByCarrinhoAndProduto(carrinho, produto);

        if (existingItemOptional.isPresent()) {
            ItemCarrinho item = existingItemOptional.get();
            if (quantidade == null || quantidade >= item.getQuantidade()) {
                // Remove totalmente o item
                itemCarrinhoRepository.delete(item);
                carrinho.getItens().remove(item); // Remove do carrinho em memória
            } else {
                // Remove parcialmente
                item.setQuantidade(item.getQuantidade() - quantidade);
                itemCarrinhoRepository.save(item);
            }
        } else {
            throw new ResourceNotFoundException("Produto não encontrado no carrinho.");
        }
        return carrinho;
    }

    public CarrinhoCompras getCarrinhoByCliente(Cliente cliente) {
        return carrinhoComprasRepository.findByCliente(cliente)
                .orElse(null); // Retorna null se não houver carrinho
    }

    public void limparCarrinho(Cliente cliente) {
        CarrinhoCompras carrinho = carrinhoComprasRepository.findByCliente(cliente)
                .orElseThrow(() -> new ResourceNotFoundException("Carrinho não encontrado para o cliente."));
        itemCarrinhoRepository.deleteAll(carrinho.getItens());
        carrinho.getItens().clear(); // Limpa a lista em memória
        carrinhoComprasRepository.save(carrinho); // Salva o carrinho com itens removidos
    }

    public Double calcularSubtotal(Cliente cliente) {
        CarrinhoCompras carrinho = carrinhoComprasRepository.findByCliente(cliente)
                .orElse(null);
        if (carrinho == null || carrinho.getItens().isEmpty()) {
            return 0.0;
        }
        return carrinho.getItens().stream()
                .mapToDouble(item -> item.getProduto().getPreco() * item.getQuantidade())
                .sum();
    }
}
