package com.aimup.repository;

import com.aimup.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findByGrupoIdAndAtivaTrueOrderByIdAsc(Long grupoId);
    Optional<Tarefa> findByIdAndGrupoId(Long id, Long grupoId);
    List<Tarefa> findByGrupoId(Long grupoId);
}
