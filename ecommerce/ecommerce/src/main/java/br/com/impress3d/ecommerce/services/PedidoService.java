package br.com.impress3d.ecommerce.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.impress3d.ecommerce.dtos.FinalizarPedidoDTO;
import br.com.impress3d.ecommerce.model.enums.StatusPedido;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
public class PedidoService {

	public void atualizarStatus(Long pedidoId, @NotNull StatusPedido status) {
		// TODO Auto-generated method stub
		
	}

	public Void finalizarPedido(String username, @Valid FinalizarPedidoDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Void> listarPedidosPorCliente(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
