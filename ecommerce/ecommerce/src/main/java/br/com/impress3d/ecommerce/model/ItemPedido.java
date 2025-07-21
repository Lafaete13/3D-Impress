package br.com.impress3d.ecommerce.model;

import java.math.BigDecimal;

import br.com.impress3d.ecommerce.model.keys.ItemPedidoId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ItemPedido")
public class ItemPedido {
    @EmbeddedId
    private ItemPedidoId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idPedido")
    @JoinColumn(name = "idPedido")
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idProduto")
    @JoinColumn(name = "idProduto")
    private Produto produto;

    private int quantidade;
    private BigDecimal precoUnitario;
}
	