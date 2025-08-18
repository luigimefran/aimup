package com.aimup.repository;

import com.aimup.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {
    List<Grupo> findByNomeContainingIgnoreCase(String nome);
    Optional<Grupo> findByNomeIgnoreCase(String nome);
}
