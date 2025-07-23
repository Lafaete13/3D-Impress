package com.impress3d.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.impress3d.demo.model.Administrador;

public interface AdminRepository extends JpaRepository<Administrador, Integer> {
	Optional<Administrador> findByEmail(String email);
}
