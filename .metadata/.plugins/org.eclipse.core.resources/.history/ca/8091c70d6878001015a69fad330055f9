package com.aimup.service;

import com.aimup.model.Usuario;
import com.aimup.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void salvarUsuario(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuario.setPontuacao(0);
        usuarioRepository.save(usuario);
    }

    public boolean emailExistente(String email) {
        return usuarioRepository.findByEmail(email) != null;
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public void atualizarPerfil(String email, Usuario novo) {
        Usuario atual = usuarioRepository.findByEmail(email);
        atual.setNome(novo.getNome());
        atual.setFotoPerfil(novo.getFotoPerfil());
        usuarioRepository.save(atual);
    }

    public List<Usuario> listarPorPontuacaoDesc() {
        return usuarioRepository.findAllByOrderByPontuacaoDesc();
    }
}