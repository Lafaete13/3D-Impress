package br.com.impress3d.ecommerce.dtos;

import br.com.impress3d.ecommerce.model.enums.MetodoPagamento;
import jakarta.validation.constraints.NotNull;

public record FinalizarPedidoDTO(
	    @NotNull MetodoPagamento metodoPagamento,
	    String enderecoEntrega // Opcional, se for diferente do cadastrado
	) {}