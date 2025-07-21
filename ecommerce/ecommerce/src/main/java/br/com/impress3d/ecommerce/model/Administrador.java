package br.com.impress3d.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Administrador")
public class Administrador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAdm;
    private String nome;
    @Column(unique = true)
    private String email;
    private String senha;
    
    public Administrador() {
    	
    }

    
	public Administrador(Long idAdm, String nome, String email, String senha) {
		this.idAdm = idAdm;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}


	public Long getIdAdm() {
		return idAdm;
	}


	public void setIdAdm(Long idAdm) {
		this.idAdm = idAdm;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}
    
    
}