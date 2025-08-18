package com.aimup.service;

import com.aimup.model.Lembrete;
import com.aimup.model.Usuario;
import com.aimup.repository.LembreteRepository;
import com.aimup.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LembreteService {

    @Autowired
    private LembreteRepository lembreteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Lembrete> getLembretesDoUsuario(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        return lembreteRepository.findByUsuario(usuario);
    }
}