package com.aimup.controller;

import com.aimup.model.Grupo;
import com.aimup.model.Usuario;
import com.aimup.repository.GrupoRepository;
import com.aimup.service.TarefaService;
import com.aimup.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/tarefas")
public class TarefaController {

    private final TarefaService tarefaService;
    private final UsuarioService usuarioService;
    private final GrupoRepository grupoRepo;

    public TarefaController(TarefaService tarefaService,
                            UsuarioService usuarioService,
                            GrupoRepository grupoRepo) {
        this.tarefaService = tarefaService;
        this.usuarioService = usuarioService;
        this.grupoRepo = grupoRepo;
    }

    @GetMapping("/grupo/{grupoId}")
    public String checklist(@PathVariable Long grupoId, Principal principal, Model model, RedirectAttributes ra) {
        try {
            Usuario u = usuarioService.buscarPorEmail(principal.getName());
            Grupo g = grupoRepo.findById(grupoId).orElseThrow();

            var tarefas = tarefaService.listarAtivasDoGrupoParaUsuario(grupoId, u.getId());
            var concluidas = tarefaService.idsTarefasConcluidasPeriodoAtual(u.getId(), grupoId);
            int pontos = tarefaService.pontosDoUsuarioNoGrupo(u.getId(), grupoId);
            int progresso = tarefaService.progressoPercentual(g, u.getId());

            model.addAttribute("grupoId", grupoId);
            model.addAttribute("tarefas", tarefas);
            model.addAttribute("concluidas", concluidas);
            model.addAttribute("pontos", pontos);
            model.addAttribute("progresso", progresso);
            model.addAttribute("grupo", g);
            return "grupo/index";
        } catch (Exception e) {
            ra.addFlashAttribute("erro", "Erro: " + e.getMessage());
            return "redirect:/grupos/" + grupoId;
        }
    }

    @PostMapping("/{tarefaId}/concluir")
    public String concluir(@PathVariable Long tarefaId,
                           @RequestParam Long grupoId,
                           Principal principal,
                           RedirectAttributes ra) {
        try {
            Usuario u = usuarioService.buscarPorEmail(principal.getName());
            tarefaService.marcarConclusao(tarefaId, u);
            ra.addFlashAttribute("ok", "Concluída!");
        } catch (Exception e) {
            ra.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/tarefas/grupo/" + grupoId;
    }
}
