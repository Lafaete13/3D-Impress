package br.com.impress3d.ecommerce.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public record ClienteUpdateDTO(
	    @NotBlank String nome,
	    @NotNull @Past LocalDate dataNascimento,
	    @NotBlank String telefone,
	    @NotBlank String endereco
	) {}