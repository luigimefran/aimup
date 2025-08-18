package com.aimup.service;

import com.aimup.model.Grupo;
import com.aimup.model.GrupoMembro;
import com.aimup.model.Tarefa;
import com.aimup.model.Usuario;
import com.aimup.repository.GrupoMembrosRepository;
import com.aimup.repository.GrupoRepository;
import com.aimup.repository.TarefaRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class GrupoService {

    private final GrupoRepository grupoRepo;
    private final GrupoMembrosRepository membroRepo;
    private final TarefaRepository tarefaRepo;

    public GrupoService(GrupoRepository grupoRepo,
                        GrupoMembrosRepository membroRepo,
                        TarefaRepository tarefaRepo) {
        this.grupoRepo = grupoRepo;
        this.membroRepo = membroRepo;
        this.tarefaRepo = tarefaRepo;
    }

    @Transactional
    public Grupo criarGrupoComTarefas(String nome,
                                      String metaTitulo,
                                      String metaDescricao,
                                      Usuario criador,
                                      List<Tarefa> tarefas,
                                      LocalDate dataLimite) {
        Grupo g = new Grupo();
        g.setNome(nome);
        g.setCriadoPor(criador);
        g.setAtivo(true);
        g.setMetaTitulo(metaTitulo);
        g.setMetaDescricao(metaDescricao);
        g.setDataLimite(dataLimite);
        grupoRepo.save(g);

        // criador ADMIN
        GrupoMembro gm = new GrupoMembro();
        gm.setGrupo(g);
        gm.setUsuario(criador);
        gm.setPapel(GrupoMembro.PapelNoGrupo.ADMIN);
        membroRepo.save(gm);

        // tarefas iniciais
        if (tarefas != null) {
            for (Tarefa t : tarefas) {
                t.setGrupo(g);
                if (t.getPontos() == null) t.setPontos(5);
                if (t.getFrequencia() == null) t.setFrequencia(Tarefa.Frequencia.SEMANAL);
                t.setAtiva(true);
                tarefaRepo.save(t);
            }
        }
        return g;
    }

    public void garantirMembro(Long grupoId, Long usuarioId) {
        if (!membroRepo.existsByGrupoIdAndUsuarioId(grupoId, usuarioId)) {
            throw new AccessDeniedException("Você não pertence a este grupo.");
        }
    }

    @Transactional
    public void entrarNoGrupo(Long grupoId, Usuario usuario) {
        if (membroRepo.existsByGrupoIdAndUsuarioId(grupoId, usuario.getId())) return;
        Grupo g = grupoRepo.findById(grupoId).orElseThrow();
        GrupoMembro gm = new GrupoMembro();
        gm.setGrupo(g);
        gm.setUsuario(usuario);
        gm.setPapel(GrupoMembro.PapelNoGrupo.MEMBRO);
        membroRepo.save(gm);
    }

    public List<Grupo> buscarPorNome(String termo) {
        return grupoRepo.findByNomeContainingIgnoreCase(termo == null ? "" : termo.trim());
    }

    @Transactional
    public void atualizarBasico(Long grupoId, Usuario solicitante, String nome, String metaTitulo, String metaDescricao) {
        Grupo g = grupoRepo.findById(grupoId).orElseThrow();
        var gm = membroRepo.findByGrupoIdAndUsuarioId(grupoId, solicitante.getId())
                .orElseThrow(() -> new AccessDeniedException("Você não pertence a este grupo."));
        if (gm.getPapel() != GrupoMembro.PapelNoGrupo.ADMIN) {
            throw new AccessDeniedException("Apenas ADMIN pode editar o grupo.");
        }
        g.setNome(nome);
        g.setMetaTitulo(metaTitulo);
        g.setMetaDescricao(metaDescricao);
        grupoRepo.save(g);
    }

    @Transactional
    public void atualizarMeta(Long grupoId, Usuario solicitante, String metaTitulo, String metaDescricao) {
        atualizarBasico(grupoId, solicitante, grupoRepo.findById(grupoId).orElseThrow().getNome(), metaTitulo, metaDescricao);
    }
}
