package br.com.impress3d.ecommerce.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClienteRegistroDTO(
	    @NotBlank String nome,
	    @NotBlank @Email String email,
	    @NotBlank @Size(min = 6) String senha,
	    @NotBlank @Pattern(regexp = "\\d{11}") String cpf,
	    @NotNull @Past LocalDate dataNascimento,
	    @NotBlank String telefone,
	    @NotBlank String endereco
	) {}