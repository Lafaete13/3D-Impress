package br.com.impress3d.ecommerce.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ItemCarrinhoDTO(
	    @NotNull Long produtoId,
	    @NotNull @Min(1) int quantidade
	) {}
