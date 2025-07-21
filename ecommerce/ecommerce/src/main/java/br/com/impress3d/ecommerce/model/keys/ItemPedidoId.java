package br.com.impress3d.ecommerce.model.keys;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class ItemPedidoId implements java.io.Serializable {
    private Long idPedido;
    private Long idProduto;
}