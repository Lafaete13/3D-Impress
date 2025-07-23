package com.impress3d.demo.services;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.impress3d.demo.configurations.JwtService;
import com.impress3d.demo.exceptions.ResourceNotFoundException;
import com.impress3d.demo.model.Administrador;
import com.impress3d.demo.model.Cliente;
import com.impress3d.demo.repositories.AdminRepository;
import com.impress3d.demo.repositories.ClienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private ClienteRepository clienteRepository;

	private AdminRepository administradorRepository;
	private PasswordEncoder passwordEncoder;
	private JwtService jwtService;
	private AuthenticationManager authenticationManager;
	private UserDetailsService userDetailsService; // Para carregar usuários

	public String registerCliente(Cliente cliente) {
		if (clienteRepository.findByEmail(cliente.getUsername()).isPresent() // getUsername retorna o email
				|| administradorRepository.findByEmail(cliente.getUsername()).isPresent()) {
			throw new IllegalArgumentException("Email já cadastrado.");
		}
		cliente.setSenha(passwordEncoder.encode(cliente.getPassword()));
		clienteRepository.save(cliente);
		return jwtService.generateToken(cliente);
	}

	public String registerAdmin(Administrador admin) {
		if (administradorRepository.findByEmail(admin.getUsername()).isPresent() //getUsername retorna email
				|| clienteRepository.findByEmail(admin.getUsername()).isPresent()) {
			throw new IllegalArgumentException("Email já cadastrado.");
		}
		admin.setSenha(passwordEncoder.encode(admin.getPassword()));
		administradorRepository.save(admin);
		return jwtService.generateToken(admin);
	}

	public String authenticate(String email, String senha) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, senha));
		UserDetails userDetails = userDetailsService.loadUserByUsername(email);
		return jwtService.generateToken(userDetails);
	}

	public void resetPassword(String email, String newPassword) {
		Optional<Cliente> clienteOptional = clienteRepository.findByEmail(email);
		Optional<Administrador> adminOptional = administradorRepository.findByEmail(email);

		if (clienteOptional.isPresent()) {
			Cliente cliente = clienteOptional.get();
			cliente.setSenha(passwordEncoder.encode(newPassword));
			clienteRepository.save(cliente);
		} else if (adminOptional.isPresent()) {
			Administrador admin = adminOptional.get();
			admin.setSenha(passwordEncoder.encode(newPassword));
			administradorRepository.save(admin);
		} else {
			throw new ResourceNotFoundException("Usuário com email " + email + " não encontrado.");
		}
	}
}