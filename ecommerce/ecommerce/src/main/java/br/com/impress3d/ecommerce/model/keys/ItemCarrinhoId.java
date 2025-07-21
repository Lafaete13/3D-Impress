package br.com.impress3d.ecommerce.model.keys;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class ItemCarrinhoId implements java.io.Serializable {
    private Long idCarr;
    private Long idProduto;
}