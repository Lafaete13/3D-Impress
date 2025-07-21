package br.com.impress3d.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.impress3d.ecommerce.dtos.AuthRequestDTO;
import br.com.impress3d.ecommerce.dtos.AuthResponseDTO;
import br.com.impress3d.ecommerce.dtos.ClienteRegistroDTO;
import br.com.impress3d.ecommerce.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
	
	@Autowired
	private AuthService authService;

    // RF01: Permitir que o usuário cadastre uma conta
    @PostMapping("/registrar")
    public ResponseEntity<AuthResponseDTO> registrar(@RequestBody @Valid ClienteRegistroDTO dto) {
        return ResponseEntity.ok(authService.registrar(dto));
    }

    // RF02: Permitir que o usuário acesse sua conta
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid AuthRequestDTO dto) {
        return ResponseEntity.ok(authService.login(dto));
    }
    
    // RF03: Permitir que o usuário recupere a senha (Endpoint inicial)
    // A implementação completa requer um serviço de e-mail
    @PostMapping("/recuperar-senha")
    public ResponseEntity<String> recuperarSenha(@RequestParam String email) {
        // authService.processarRecuperacaoSenha(email);
        return ResponseEntity.ok("Se o e-mail estiver cadastrado, um link de recuperação será enviado.");
    }
}