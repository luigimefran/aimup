package com.aimup.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tarefas_conclusoes",
       uniqueConstraints = @UniqueConstraint(columnNames = {"tarefa_id","usuario_id","periodo_chave"}))
public class TarefaConclusao {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) @JoinColumn(name = "tarefa_id")
    private Tarefa tarefa;

    @ManyToOne(optional = false) @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "periodo_chave", nullable = false, length = 16)
    private String periodoChave; // ex: 2025-08-18 (DIARIA) ou 2025-W33 (SEMANAL)

    @Column(name = "concluida_em", nullable = false)
    private LocalDateTime concluidaEm = LocalDateTime.now();

    // getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Tarefa getTarefa() { return tarefa; }
    public void setTarefa(Tarefa tarefa) { this.tarefa = tarefa; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public String getPeriodoChave() { return periodoChave; }
    public void setPeriodoChave(String periodoChave) { this.periodoChave = periodoChave; }
    public LocalDateTime getConcluidaEm() { return concluidaEm; }
    public void setConcluidaEm(LocalDateTime concluidaEm) { this.concluidaEm = concluidaEm; }
}
