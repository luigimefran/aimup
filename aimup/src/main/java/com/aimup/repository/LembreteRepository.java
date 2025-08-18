package com.aimup.repository;

import com.aimup.model.Lembrete;
import com.aimup.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LembreteRepository extends JpaRepository<Lembrete, Long> {

    @Transactional(readOnly = true)
    List<Lembrete> findByUsuarioAndDataHoraAfterOrderByDataHoraAsc(Usuario u, LocalDateTime agora);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    long deleteByUsuarioAndDataHoraBefore(Usuario u, LocalDateTime agora);

    @Transactional(readOnly = true)
    Optional<Lembrete> findByIdAndUsuarioId(Long id, Long usuarioId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    long deleteByIdAndUsuarioId(Long id, Long usuarioId);
}
