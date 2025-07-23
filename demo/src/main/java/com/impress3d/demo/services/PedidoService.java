package com.impress3d.demo.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.impress3d.demo.exceptions.ResourceNotFoundException;
import com.impress3d.demo.model.CarrinhoCompras;
import com.impress3d.demo.model.Cliente;
import com.impress3d.demo.model.ItemCarrinho;
import com.impress3d.demo.model.ItemPedido;
import com.impress3d.demo.model.Pagamento;
import com.impress3d.demo.model.Pedido;
import com.impress3d.demo.model.Produto;
import com.impress3d.demo.repositories.CarrinhoComprasRepository;
import com.impress3d.demo.repositories.ItemPedidoRepository;
import com.impress3d.demo.repositories.PagamentoRepository;
import com.impress3d.demo.repositories.PedidoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private CarrinhoComprasRepository carrinhoComprasRepository;
    @Autowired
    private ProdutoService produtoService; // Para atualizar estoque

    @Transactional
    public Pedido finalizarPedido(Cliente cliente, String metodoPagamento, String enderecoEntrega) {
        CarrinhoCompras carrinho = carrinhoComprasRepository.findByCliente(cliente)
                .orElseThrow(() -> new IllegalArgumentException("Carrinho vazio ou não encontrado para o cliente."));

        if (carrinho.getItens().isEmpty()) {
            throw new IllegalArgumentException("Carrinho de compras está vazio.");
        }

        // 1. Criar o Pedido
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setStatus("Pendente"); // Status inicial do pedido
        pedido.setEnderecoEntrega(enderecoEntrega != null ? enderecoEntrega : cliente.getEndereco()); // RF14
        pedido = pedidoRepository.save(pedido);

        // 2. Mover itens do carrinho para ItemPedido e atualizar estoque
        List<ItemPedido> itensPedido = new ArrayList<>();
        for (ItemCarrinho itemCarrinho : carrinho.getItens()) {
            Produto produto = itemCarrinho.getProduto();
            Integer quantidade = itemCarrinho.getQuantidade();

            // Verificar e decrementar estoque
            if (produto.getValorEst() != null && produto.getValorEst() < quantidade) {
                throw new IllegalArgumentException("Estoque insuficiente para o produto: " + produto.getNome());
            }
            if (produto.getValorEst() != null) {
                produto.setValorEst(produto.getValorEst() - quantidade);
                produtoService.updateProduto(produto.getIdProduto(), produto); // Atualiza o produto no banco
            }

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(produto);
            itemPedido.setQuantidade(quantidade);
            itemPedido.setPrecoUnitario(produto.getPreco());
            itensPedido.add(itemPedido);
        }
        itemPedidoRepository.saveAll(itensPedido);
        pedido.setItens(itensPedido);

        // 3. Criar o Pagamento
        Pagamento pagamento = new Pagamento();
        pagamento.setPedido(pedido);
        pagamento.setValorPago(carrinho.getItens().stream()
                .mapToDouble(item -> item.getProduto().getPreco() * item.getQuantidade())
                .sum());
        pagamento.setMetodoPay(metodoPagamento);
        pagamento.setStatusPay("Pendente"); // Status inicial do pagamento
        pagamento.setDataPay(LocalDateTime.now());
        pagamentoRepository.save(pagamento);
        pedido.setPagamento(pagamento);

        // 4. Limpar o carrinho
        carrinhoComprasRepository.delete(carrinho);

        return pedidoRepository.save(pedido);
    }

    public List<Pedido> getPedidosByCliente(Cliente cliente) {
        return pedidoRepository.findByCliente(cliente);
    }

    public Pedido getPedidoById(Integer idPedido) {
        return pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado com ID: " + idPedido));
    }

    public Pedido updateStatusPedido(Integer idPedido, String newStatus) {
        Pedido pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado com ID: " + idPedido));

        // Validações de status (opcional, mas recomendado)
        List<String> validStatuses = Arrays.asList("Pendente", "Aprovado", "Rejeitado", "Em Processamento", "Enviado", "Entregue", "Cancelado");
        if (!validStatuses.contains(newStatus)) {
            throw new IllegalArgumentException("Status inválido: " + newStatus);
        }

        pedido.setStatus(newStatus);
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }
}
