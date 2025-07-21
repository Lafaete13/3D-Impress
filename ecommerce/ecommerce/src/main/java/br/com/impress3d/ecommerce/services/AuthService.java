package br.com.impress3d.ecommerce.services;

import org.springframework.stereotype.Service;

import br.com.impress3d.ecommerce.dtos.AuthRequestDTO;
import br.com.impress3d.ecommerce.dtos.AuthResponseDTO;
import br.com.impress3d.ecommerce.dtos.ClienteRegistroDTO;
import jakarta.validation.Valid;

@Service
public class AuthService {

	public AuthResponseDTO registrar(@Valid ClienteRegistroDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	public AuthResponseDTO login(@Valid AuthRequestDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
