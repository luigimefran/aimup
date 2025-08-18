package com.aimup.repository;

import com.aimup.model.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
    List<Mensagem> findTop100ByGrupoIdOrderByIdDesc(Long grupoId);
}
