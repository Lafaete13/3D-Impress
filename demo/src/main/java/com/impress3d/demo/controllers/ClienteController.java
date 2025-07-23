package com.impress3d.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.impress3d.demo.model.Cliente;
import com.impress3d.demo.services.ClienteService;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // RF04: Editar dados cadastrais (exceto CPF e e-mail)
    @PutMapping("/perfil")
    public ResponseEntity<Cliente> updateCliente(@AuthenticationPrincipal Cliente clienteAutenticado, @RequestBody Cliente clienteDetails) {
        Cliente updatedCliente = clienteService.updateCliente(clienteAutenticado.getIdCliente(), clienteDetails);
        return ResponseEntity.ok(updatedCliente);
    }

    // Endpoint para o próprio cliente visualizar seus dados
    @GetMapping("/perfil")
    public ResponseEntity<Cliente> getMyProfile(@AuthenticationPrincipal Cliente clienteAutenticado) {
        return ResponseEntity.ok(clienteAutenticado);
    }

    // Apenas para administradores: listar todos os clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
        return ResponseEntity.ok(clienteService.getAllClientes());
    }

    // Apenas para administradores: visualizar cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) {
        return ResponseEntity.ok(clienteService.getClienteById(id));
    }

    // Apenas para administradores: deletar cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Integer id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }
}
