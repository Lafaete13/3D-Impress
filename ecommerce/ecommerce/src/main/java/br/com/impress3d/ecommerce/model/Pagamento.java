package br.com.impress3d.ecommerce.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.impress3d.ecommerce.model.enums.MetodoPagamento;
import br.com.impress3d.ecommerce.model.enums.StatusPagamento;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Pagamento")
public class Pagamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPay;
	private BigDecimal valorPago;

	@Enumerated(EnumType.STRING)
	private MetodoPagamento metodoPay;

	@Enumerated(EnumType.STRING)
	private StatusPagamento statusPay;

	private LocalDateTime dataPay;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idPedido")
	private Pedido pedido;
}
