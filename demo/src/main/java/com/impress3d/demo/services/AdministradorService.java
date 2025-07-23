package com.impress3d.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.impress3d.demo.exceptions.ResourceNotFoundException;
import com.impress3d.demo.model.Administrador;
import com.impress3d.demo.repositories.AdminRepository;

@Service
public class AdministradorService {

    @Autowired
    private AdminRepository administradorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Administrador createAdministrador(Administrador administrador) {
        administrador.setSenha(passwordEncoder.encode(administrador.getPassword()));
        return administradorRepository.save(administrador);
    }

    public List<Administrador> getAllAdministradores() {
        return administradorRepository.findAll();
    }

    public Administrador getAdministradorById(Integer id) {
        return administradorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Administrador não encontrado com ID: " + id));
    }

    public Administrador updateAdministrador(Integer id, Administrador administradorDetails) {
        Administrador administrador = administradorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Administrador não encontrado com ID: " + id));

        administrador.setNome(administradorDetails.getNome());
        administrador.setEmail(administradorDetails.getUsername()); // getUsername retorna o email
        // Não atualiza a senha aqui, deve ser um endpoint separado para mudança de senha
        return administradorRepository.save(administrador);
    }

    public void deleteAdministrador(Integer id) {
        Administrador administrador = administradorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Administrador não encontrado com ID: " + id));
        administradorRepository.delete(administrador);
    }
}
