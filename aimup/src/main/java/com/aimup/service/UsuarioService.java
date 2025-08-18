package com.aimup.service;

import com.aimup.model.Usuario;
import com.aimup.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean emailExistente(String email) {
        return usuarioRepository.findByEmail(email) != null;
    }

    /**
     * Uso: cadastro/registro de usuário novo (com senha em texto puro).
     */
    @Transactional
    public Usuario salvarUsuario(Usuario usuario) {
        String raw = usuario.getSenha();
        if (raw == null || raw.isBlank()) {
            throw new IllegalArgumentException("Senha não pode ser vazia");
        }
        usuario.setSenha(passwordEncoder.encode(raw));
        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    /**
     * Atualiza dados de perfil pelo e-mail do usuário logado,
     * sem alterar a senha já armazenada.
     */
    @Transactional
    public void atualizarPerfil(String email, Usuario novo) {
        Usuario atual = usuarioRepository.findByEmail(email);
        if (atual == null) {
            throw new IllegalArgumentException("Usuário não encontrado para atualização");
        }
        if (novo.getNome() != null && !novo.getNome().isBlank()) {
            atual.setNome(novo.getNome());
        }
        if (novo.getFotoPerfil() != null && !novo.getFotoPerfil().isBlank()) {
            atual.setFotoPerfil(novo.getFotoPerfil());
        }
        // NÃO alterar senha aqui
        usuarioRepository.save(atual);
    }

    /**
     * Atualiza atributos permitidos SEM re-encodar senha.
     * Ideal para atualizar foto de perfil e/ou nome.
     *
     * Importante: Recarrega do banco para evitar sobrescrever a senha.
     */
    @Transactional
    public Usuario salvarUsuarioSemTrocarSenha(Usuario usuarioAtualizado) {
        if (usuarioAtualizado == null || usuarioAtualizado.getId() == null) {
            throw new IllegalArgumentException("ID do usuário é obrigatório para atualização segura.");
        }

        Usuario db = usuarioRepository.findById(usuarioAtualizado.getId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

        // Atualize apenas os campos permitidos (sem tocar na senha)
        if (usuarioAtualizado.getNome() != null && !usuarioAtualizado.getNome().isBlank()) {
            db.setNome(usuarioAtualizado.getNome());
        }
        if (usuarioAtualizado.getFotoPerfil() != null && !usuarioAtualizado.getFotoPerfil().isBlank()) {
            db.setFotoPerfil(usuarioAtualizado.getFotoPerfil());
        }
        // NÃO mexer em db.setSenha(...)

        return usuarioRepository.save(db);
    }

    public List<Usuario> listarPorPontuacaoDesc() {
        return usuarioRepository.findAllByOrderByPontuacaoDesc();
    }
}
