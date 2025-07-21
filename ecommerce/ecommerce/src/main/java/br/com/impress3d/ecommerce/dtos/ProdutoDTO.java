package br.com.impress3d.ecommerce.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProdutoDTO(Long id, @NotBlank String nome, String descricao, @NotNull @Positive BigDecimal preco,
		@NotNull @Min(0) Integer estoque, @NotBlank String categoria) {
}
