package com.aimup.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "grupos")
public class Grupo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String nome;

    @ManyToOne(optional = false)
    @JoinColumn(name="criado_por_id")
    private Usuario criadoPor;

    @Column(nullable = false)
    private Boolean ativo = true;

    private String metaTitulo;
    @Column(length = 2000)
    private String metaDescricao;

    /** Data final do desafio (inclusive). Depois disso o grupo fica somente leitura. */
    private LocalDate dataLimite;

    @Column(nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @PrePersist
    public void prePersist(){
        if (criadoEm == null) criadoEm = LocalDateTime.now();
    }

    // ===== helpers =====
    @Transient
    public boolean isExpirado() {
        return dataLimite != null && LocalDate.now().isAfter(dataLimite);
    }

    // ===== getters/setters =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Usuario getCriadoPor() { return criadoPor; }
    public void setCriadoPor(Usuario criadoPor) { this.criadoPor = criadoPor; }
    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
    public String getMetaTitulo() { return metaTitulo; }
    public void setMetaTitulo(String metaTitulo) { this.metaTitulo = metaTitulo; }
    public String getMetaDescricao() { return metaDescricao; }
    public void setMetaDescricao(String metaDescricao) { this.metaDescricao = metaDescricao; }
    public LocalDate getDataLimite() { return dataLimite; }
    public void setDataLimite(LocalDate dataLimite) { this.dataLimite = dataLimite; }
    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }
}
