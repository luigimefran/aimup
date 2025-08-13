
package com.aimup.repository;

import com.aimup.model.Lembrete;
import com.aimup.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LembreteRepository extends JpaRepository<Lembrete, Long> {
    List<Lembrete> findByUsuario(Usuario usuario);
}
