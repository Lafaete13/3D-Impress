package br.com.impress3d.ecommerce.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.impress3d.ecommerce.dtos.ClienteRegistroDTO;
import br.com.impress3d.ecommerce.dtos.ClienteUpdateDTO;
import br.com.impress3d.ecommerce.services.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

	private ClienteService clienteService;

	// RF04: Permitir que o usuário edite seus dados cadastrais
	@PutMapping("/me")
	public ResponseEntity<Void> atualizarCliente(@RequestBody @Valid ClienteUpdateDTO dto,
			@AuthenticationPrincipal UserDetails userDetails) {
		clienteService.atualizar(userDetails.getUsername(), dto);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/me")
	public ResponseEntity<ClienteRegistroDTO> getClienteInfo(@AuthenticationPrincipal UserDetails userDetails) {
		return ResponseEntity.ok(clienteService.getClienteInfo(userDetails.getUsername()));
	}
}