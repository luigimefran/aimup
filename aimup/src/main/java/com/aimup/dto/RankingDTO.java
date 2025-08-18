package com.aimup.dto;

public class RankingDTO {
    private Long usuarioId;
    private String nome;
    private String fotoPerfil;
    private Long pontos; 
    
    public RankingDTO(Long usuarioId, String nome, String fotoPerfil, Long pontos) {
        this.usuarioId = usuarioId;
        this.nome = nome;
        this.fotoPerfil = fotoPerfil;
        this.pontos = pontos;
    }

    public Long getUsuarioId() { return usuarioId; }
    public String getNome() { return nome; }
    public String getFotoPerfil() { return fotoPerfil; }
    public Long getPontos() { return pontos; }
}