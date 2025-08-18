package com.aimup.service;

import com.aimup.model.Grupo;
import com.aimup.model.GrupoMembro;
import com.aimup.model.Tarefa;
import com.aimup.model.TarefaConclusao;
import com.aimup.model.Usuario;
import com.aimup.repository.GrupoMembrosRepository;
import com.aimup.repository.TarefaConclusaoRepository;
import com.aimup.repository.TarefaRepository;
import com.aimup.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepo;
    private final TarefaConclusaoRepository conclusaoRepo;
    private final GrupoMembrosRepository membroRepo;
    private final UsuarioRepository usuarioRepo;

    public TarefaService(TarefaRepository tarefaRepo,
                         TarefaConclusaoRepository conclusaoRepo,
                         GrupoMembrosRepository membroRepo,
                         UsuarioRepository usuarioRepo) {
        this.tarefaRepo = tarefaRepo;
        this.conclusaoRepo = conclusaoRepo;
        this.membroRepo = membroRepo;
        this.usuarioRepo = usuarioRepo;
    }

    public List<Tarefa> listarAtivasDoGrupoParaUsuario(Long grupoId, Long usuarioId) {
        if (!membroRepo.existsByGrupoIdAndUsuarioId(grupoId, usuarioId)) {
            throw new AccessDeniedException("Você não pertence a este grupo.");
        }
        return tarefaRepo.findByGrupoIdAndAtivaTrueOrderByIdAsc(grupoId);
    }

    private String periodoChave(Tarefa t, LocalDate hoje) {
        if (t.getFrequencia() == Tarefa.Frequencia.DIARIA) {
            return hoje.toString(); // yyyy-MM-dd
        } else {
            WeekFields wf = WeekFields.of(Locale.getDefault());
            int week = hoje.get(wf.weekOfWeekBasedYear());
            int year = hoje.get(wf.weekBasedYear());
            return year + "-W" + String.format("%02d", week); // ex 2025-W33
        }
    }

    @Transactional
    public void marcarConclusao(Long tarefaId, Usuario usuario) {
        Tarefa tarefa = tarefaRepo.findById(tarefaId)
                .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada"));

        // trava se grupo expirou
        if (tarefa.getGrupo().getDataLimite() != null
                && LocalDate.now().isAfter(tarefa.getGrupo().getDataLimite())) {
            throw new AccessDeniedException("O desafio deste grupo já encerrou.");
        }

        if (!membroRepo.existsByGrupoIdAndUsuarioId(tarefa.getGrupo().getId(), usuario.getId())) {
            throw new AccessDeniedException("Você não pertence a este grupo.");
        }

        String pc = periodoChave(tarefa, LocalDate.now());
        if (conclusaoRepo.existsByTarefaIdAndUsuarioIdAndPeriodoChave(tarefaId, usuario.getId(), pc)) return;

        TarefaConclusao c = new TarefaConclusao();
        c.setTarefa(tarefa);
        c.setUsuario(usuario);
        c.setPeriodoChave(pc);
        conclusaoRepo.save(c);

        // soma na pontuação global do usuário
        usuario.setPontuacao((usuario.getPontuacao() == 0 ? 0 : usuario.getPontuacao()) + tarefa.getPontos());
        usuarioRepo.save(usuario);
    }

    public int pontosDoUsuarioNoGrupo(Long usuarioId, Long grupoId) {
        Integer soma = conclusaoRepo.somaPontosPorUsuarioEGrupoTotal(usuarioId, grupoId);
        return soma == null ? 0 : soma;
    }

    public List<Long> idsTarefasConcluidasPeriodoAtual(Long usuarioId, Long grupoId) {
        LocalDate hoje = LocalDate.now();
        String periodoDiario = hoje.toString();

        var wf = WeekFields.of(Locale.getDefault());
        String periodoSemanal = hoje.get(wf.weekBasedYear()) + "-W" +
                String.format("%02d", hoje.get(wf.weekOfWeekBasedYear()));

        var diarias = conclusaoRepo.idsConcluidasNoPeriodo(usuarioId, grupoId, periodoDiario);
        var semanais = conclusaoRepo.idsConcluidasNoPeriodo(usuarioId, grupoId, periodoSemanal);

        return Stream.concat(diarias.stream(), semanais.stream())
                .distinct()
                .collect(Collectors.toList());
    }

    // Progresso: pontos ganhos / pontos máximos possíveis até agora
    public int progressoPercentual(Grupo grupo, Long usuarioId) {
        long max = maximoPossivelAteAgora(grupo);
        int ganhos = pontosDoUsuarioNoGrupo(usuarioId, grupo.getId());
        if (max <= 0) return 0;
        long pct = Math.round((ganhos * 100.0) / max);
        return (int) Math.max(0, Math.min(100, pct));
    }

    public long maximoPossivelAteAgora(Grupo grupo) {
        var inicio = grupo.getCriadoEm() == null ? LocalDate.now() : grupo.getCriadoEm().toLocalDate();
        var fim = grupo.getDataLimite() == null ? LocalDate.now() : grupo.getDataLimite();
        var hoje = LocalDate.now();
        var limiteConsiderado = hoje.isBefore(fim) ? hoje : fim;

        List<Tarefa> tarefas = tarefaRepo.findByGrupoIdAndAtivaTrueOrderByIdAsc(grupo.getId());

        long max = 0;
        for (Tarefa t : tarefas) {
            if (t.getFrequencia() == Tarefa.Frequencia.DIARIA) {
                long dias = java.time.temporal.ChronoUnit.DAYS.between(inicio, limiteConsiderado) + 1;
                if (dias < 0) dias = 0;
                max += dias * (long) t.getPontos();
            } else {
                long semanas = java.time.temporal.ChronoUnit.WEEKS.between(inicio, limiteConsiderado) + 1;
                if (semanas < 0) semanas = 0;
                max += semanas * (long) t.getPontos();
            }
        }
        return Math.max(0, max);
    }
}
