package com.aimup.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tarefas")
public class Tarefa {
    public enum Frequencia { DIARIA, SEMANAL }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) @JoinColumn(name = "grupo_id")
    private Grupo grupo;

    @Column(nullable = false)
    private String titulo;

    @Column(length = 2000)
    private String descricao;

    @Column(nullable = false)
    private Integer pontos;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Frequencia frequencia = Frequencia.SEMANAL; // default

    @Column(nullable = false)
    private Boolean ativa = true;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Grupo getGrupo() { return grupo; }
    public void setGrupo(Grupo grupo) { this.grupo = grupo; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Integer getPontos() { return pontos; }
    public void setPontos(Integer pontos) { this.pontos = pontos; }

    public Frequencia getFrequencia() { return frequencia; }
    public void setFrequencia(Frequencia frequencia) { this.frequencia = frequencia; }

    public Boolean getAtiva() { return ativa; }
    public void setAtiva(Boolean ativa) { this.ativa = ativa; }
}
