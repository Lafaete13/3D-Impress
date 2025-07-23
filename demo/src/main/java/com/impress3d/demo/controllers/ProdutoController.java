package com.impress3d.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.impress3d.demo.model.Administrador;
import com.impress3d.demo.model.Produto;
import com.impress3d.demo.services.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	// RF16: Administrador cadastra novos produtos
	@PostMapping("/admin")
	public ResponseEntity<Produto> createProduto(@AuthenticationPrincipal Administrador adminAutenticado,
			@RequestBody Produto produto) {
		Produto createdProduto = produtoService.createProduto(produto, adminAutenticado);
		return ResponseEntity.ok(createdProduto);
	}

	// RF17: Administrador edita dados dos produtos
	@PutMapping("/admin/{id}")
	public ResponseEntity<Produto> updateProduto(@PathVariable Integer id, @RequestBody Produto produtoDetails) {
		Produto updatedProduto = produtoService.updateProduto(id, produtoDetails);
		return ResponseEntity.ok(updatedProduto);
	}

	// RF18: Administrador remove produtos
	@DeleteMapping("/admin/{id}")
	public ResponseEntity<Void> deleteProduto(@PathVariable Integer id) {
		produtoService.deleteProduto(id);
		return ResponseEntity.noContent().build();
	}

	// RF05: Listar todos os produtos (para navegação)
	@GetMapping
	public ResponseEntity<List<Produto>> getAllProdutos() {
		return ResponseEntity.ok(produtoService.getAllProdutos());
	}

	// RF05: Listar produtos por categoria
	@GetMapping("/categoria/{categoria}")
	public ResponseEntity<List<Produto>> getProdutosByCategoria(@PathVariable String categoria) {
		return ResponseEntity.ok(produtoService.getProdutosByCategoria(categoria));
	}

	// Obter produto por ID
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getProdutoById(@PathVariable Integer id) {
		return ResponseEntity.ok(produtoService.getProdutoById(id));
	}
}
