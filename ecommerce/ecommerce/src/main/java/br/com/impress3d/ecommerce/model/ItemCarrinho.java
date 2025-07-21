package br.com.impress3d.ecommerce.model;

import br.com.impress3d.ecommerce.model.keys.ItemCarrinhoId;
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
@Table(name = "ItemCarrinho")
public class ItemCarrinho {
    @EmbeddedId
    private ItemCarrinhoId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idCarr")
    @JoinColumn(name = "idCarr")
    private CarrinhoCompras carrinho;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idProduto")
    @JoinColumn(name = "idProduto")
    private Produto produto;

    private int quantidade;
}
