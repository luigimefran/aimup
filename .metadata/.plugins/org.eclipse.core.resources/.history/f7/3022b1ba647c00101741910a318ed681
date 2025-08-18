package com.aimup.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome; 

    @Column(unique = true)
    private String email;

    private String senha;

    private String fotoPerfil;

    private int pontuacao;
    
    @Column(nullable = false)
    private Double imc = 0.0; // valor inicial 0.0 até calcular
    
    private LocalDateTime imcUpdatedAt; // opcional: quando foi atualizado pela última vez

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public Double getImc() {
        return imc;
    }

    public void setImc(Double imc) {
        this.imc = imc;
    }

    public LocalDateTime getImcUpdatedAt() {
        return imcUpdatedAt;
    }

    public void setImcUpdatedAt(LocalDateTime imcUpdatedAt) {
        this.imcUpdatedAt = imcUpdatedAt;
    }
}
