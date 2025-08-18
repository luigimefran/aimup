package com.aimup.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuario")
public class Usuario {

    public static final String DEFAULT_AVATAR = "/img/usuario-default.png";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(name = "foto_perfil")
    private String fotoPerfil;

    @Column(nullable = false)
    private int pontuacao = 0;

    @Column(nullable = false)
    private Double imc = 0.0; // valor inicial 0.0 até calcular

    private LocalDateTime imcUpdatedAt; // opcional: quando foi atualizado pela última vez

    /* ====== Defaults ao criar ====== */
    @PrePersist
    private void onCreate() {
        if (fotoPerfil == null || fotoPerfil.isBlank()) {
            fotoPerfil = DEFAULT_AVATAR;
        }
        if (imc == null) {
            imc = 0.0;
        }
        // pontuacao é primitivo (int) e já inicia em 0; fica aqui só por segurança
        if (pontuacao < 0) {
            pontuacao = 0;
        }
    }

    /* ====== Getters/Setters ====== */

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getFotoPerfil() { return fotoPerfil; }
    public void setFotoPerfil(String fotoPerfil) { this.fotoPerfil = fotoPerfil; }

    public int getPontuacao() { return pontuacao; }
    public void setPontuacao(int pontuacao) { this.pontuacao = pontuacao; }

    public Double getImc() { return imc; }
    public void setImc(Double imc) { this.imc = imc; }

    public LocalDateTime getImcUpdatedAt() { return imcUpdatedAt; }
    public void setImcUpdatedAt(LocalDateTime imcUpdatedAt) { this.imcUpdatedAt = imcUpdatedAt; }
}
