package com.impress3d.demo.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.impress3d.demo.model.Administrador;
import com.impress3d.demo.model.Cliente;
import com.impress3d.demo.services.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
	
	@Autowired
	private AuthService authService;

	@PostMapping("/cliente/cadastro")
	public ResponseEntity<String> registerCliente(@RequestBody Cliente cliente) {
		try {
			String token = authService.registerCliente(cliente);
			return ResponseEntity.ok(token);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PostMapping("/admin/cadastro")
	public ResponseEntity<String> registerAdmin(@RequestBody Administrador admin) {
		try {
			String token = authService.registerAdmin(admin);
			return ResponseEntity.ok(token);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Map<String, String> credentials) {
		try {
			String email = credentials.get("email");
			String senha = credentials.get("senha");
			String token = authService.authenticate(email, senha);
			return ResponseEntity.ok(token);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas.");
		}
	}

	@PostMapping("/reset-senha")
	public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> request) {
		try {
			String email = request.get("email");
			String newPassword = request.get("newPassword");
			authService.resetPassword(email, newPassword);
			return ResponseEntity.ok("Senha redefinida com sucesso.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
