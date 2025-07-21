package br.com.impress3d.ecommerce.dtos;

import br.com.impress3d.ecommerce.model.enums.StatusPedido;
import jakarta.validation.constraints.NotNull;

public record AtualizarStatusDTO(
	    @NotNull StatusPedido status
	) {}