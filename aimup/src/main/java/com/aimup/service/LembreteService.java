package com.aimup.service;

import com.aimup.model.Lembrete;
import com.aimup.model.Usuario;
import com.aimup.repository.LembreteRepository;
import com.aimup.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LembreteService {

    private final LembreteRepository lembreteRepository;
    private final UsuarioRepository usuarioRepository;

    public LembreteService(LembreteRepository lembreteRepository,
                           UsuarioRepository usuarioRepository) {
        this.lembreteRepository = lembreteRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public void salvar(String emailAutor, String titulo, String descricao, LocalDateTime quando) {
        Usuario u = usuarioRepository.findByEmail(emailAutor);
        Lembrete l = new Lembrete();
        l.setTitulo(titulo);
        l.setDescricao(descricao);
        l.setDataHora(quando);
        l.setUsuario(u);
        lembreteRepository.save(l);
    }

    @Transactional
    public void excluirSePertence(Long id, String emailAutor) {
        Usuario u = usuarioRepository.findByEmail(emailAutor);
        lembreteRepository.deleteByIdAndUsuarioId(id, u.getId());
    }

    @Transactional
    public void excluirExpiradosDoUsuario(String emailAutor) {
        Usuario u = usuarioRepository.findByEmail(emailAutor);
        lembreteRepository.deleteByUsuarioAndDataHoraBefore(u, LocalDateTime.now());
    }

    @Transactional(readOnly = true)
    public List<Lembrete> listarProximos(String emailAutor) {
        Usuario u = usuarioRepository.findByEmail(emailAutor);
        return lembreteRepository.findByUsuarioAndDataHoraAfterOrderByDataHoraAsc(u, LocalDateTime.now());
    }
}
