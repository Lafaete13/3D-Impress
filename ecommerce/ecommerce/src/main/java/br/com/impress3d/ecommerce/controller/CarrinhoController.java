package br.com.impress3d.ecommerce.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.impress3d.ecommerce.dtos.ItemCarrinhoDTO;
import br.com.impress3d.ecommerce.model.CarrinhoCompras;
import br.com.impress3d.ecommerce.services.CarrinhoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/carrinho")
@RequiredArgsConstructor
public class CarrinhoController {
    
    private CarrinhoService carrinhoService;

    // RF09: Permitir que o usuário adicione produtos ao carrinho
    @PostMapping("/adicionar")
    public ResponseEntity<Void> adicionarItem(@RequestBody @Valid ItemCarrinhoDTO itemDTO, @AuthenticationPrincipal UserDetails userDetails) {
        carrinhoService.adicionarItem(userDetails.getUsername(), itemDTO);
        return ResponseEntity.ok().build();
    }

    // RF10: Permitir que o usuário remova produtos do carrinho
    @DeleteMapping("/remover/{produtoId}")
    public ResponseEntity<Void> removerItem(@PathVariable Long produtoId, @RequestParam int quantidade, @AuthenticationPrincipal UserDetails userDetails) {
        carrinhoService.removerItem(userDetails.getUsername(), produtoId, quantidade);
        return ResponseEntity.noContent().build();
    }

    // RF11: Exibir o subtotal e os itens presentes no carrinho
    @GetMapping
    public ResponseEntity<CarrinhoCompras> verCarrinho(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(carrinhoService.verCarrinho(userDetails.getUsername()));
       // return ResponseEntity.ok().build(); // Placeholder para o DTO do carrinho
    }
}