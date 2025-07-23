package com.impress3d.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.impress3d.demo.exceptions.ResourceNotFoundException;
import com.impress3d.demo.model.Cliente;
import com.impress3d.demo.repositories.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Cliente createCliente(Cliente cliente) {
        // A criptografia da senha é feita no AuthService
        return clienteRepository.save(cliente);
    }

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Cliente getClienteById(Integer id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + id));
    }

    public Cliente updateCliente(Integer id, Cliente clienteDetails) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + id));

        cliente.setNome(clienteDetails.getNome());
        cliente.setNascimento(clienteDetails.getNascimento());
        cliente.setTelefone(clienteDetails.getTelefone());
        cliente.setEndereco(clienteDetails.getEndereco()); // RF04: Permite editar endereço
        // CPF e email não são editáveis (RF04)
        return clienteRepository.save(cliente);
    }

    public void deleteCliente(Integer id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + id));
        clienteRepository.delete(cliente);
    }
}