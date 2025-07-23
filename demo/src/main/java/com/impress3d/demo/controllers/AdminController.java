package com.impress3d.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.impress3d.demo.model.Administrador;
import com.impress3d.demo.services.AdministradorService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private AdministradorService administradorService;

	// Criar um novo administrador (pode ser feito manualmente ou por um admin
	// existente)
	@PostMapping
	public ResponseEntity<Administrador> createAdmin(@RequestBody Administrador administrador) {
		Administrador createdAdmin = administradorService.createAdministrador(administrador);
		return ResponseEntity.ok(createdAdmin);
	}

	// Obter todos os administradores
	@GetMapping
	public ResponseEntity<List<Administrador>> getAllAdmins() {
		return ResponseEntity.ok(administradorService.getAllAdministradores());
	}

	// Obter administrador por ID
	@GetMapping("/{id}")
	public ResponseEntity<Administrador> getAdminById(@PathVariable Integer id) {
		return ResponseEntity.ok(administradorService.getAdministradorById(id));
	}

	// Atualizar dados do administrador (exceto senha)
	@PutMapping("/{id}")
	public ResponseEntity<Administrador> updateAdmin(@PathVariable Integer id,
			@RequestBody Administrador administradorDetails) {
		Administrador updatedAdmin = administradorService.updateAdministrador(id, administradorDetails);
		return ResponseEntity.ok(updatedAdmin);
	}

	// Deletar administrador
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAdmin(@PathVariable Integer id) {
		administradorService.deleteAdministrador(id);
		return ResponseEntity.noContent().build();
	}
}
