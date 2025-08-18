package com.aimup.service;

import com.aimup.model.Grupo;
import com.aimup.model.Mensagem;
import com.aimup.model.Usuario;
import com.aimup.repository.GrupoMembrosRepository;
import com.aimup.repository.GrupoRepository;
import com.aimup.repository.MensagemRepository;
import com.aimup.repository.UsuarioRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ChatService {

    private final MensagemRepository mensagemRepository;
    private final UsuarioRepository usuarioRepository;
    private final GrupoRepository grupoRepository;
    private final GrupoMembrosRepository membroRepo;

    public ChatService(MensagemRepository mensagemRepository,
                       UsuarioRepository usuarioRepository,
                       GrupoRepository grupoRepository,
                       GrupoMembrosRepository membroRepo) {
        this.mensagemRepository = mensagemRepository;
        this.usuarioRepository = usuarioRepository;
        this.grupoRepository = grupoRepository;
        this.membroRepo = membroRepo;
    }

    public void salvarMensagem(Long grupoId, String emailAutor, String conteudo) {
        if (conteudo == null || conteudo.isBlank()) return;

        Usuario usuario = usuarioRepository.findByEmail(emailAutor);
        if (usuario == null) throw new IllegalArgumentException("Autor inválido");

        Grupo grupo = grupoRepository.findById(grupoId)
                .orElseThrow(() -> new IllegalArgumentException("Grupo não encontrado"));

        if (!membroRepo.existsByGrupoIdAndUsuarioId(grupoId, usuario.getId())) {
            throw new AccessDeniedException("Você não pertence a este grupo.");
        }

        Mensagem m = new Mensagem();
        m.setAutor(usuario);
        m.setGrupo(grupo);
        m.setConteudo(conteudo.trim());
        mensagemRepository.save(m);
    }

    public List<Mensagem> ultimasMensagensAsc(Long grupoId) {
        List<Mensagem> desc = mensagemRepository.findTop100ByGrupoIdOrderByIdDesc(grupoId);
        Collections.reverse(desc); // exibir em ordem cronológica crescente
        return desc;
    }

    public long qtdMembrosDoGrupo(Long grupoId) {
        return membroRepo.countByGrupoId(grupoId);
    }
}
