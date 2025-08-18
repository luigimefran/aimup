package com.aimup.controller;

import com.aimup.service.LembreteService;
import com.aimup.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/lembretes")
public class LembreteController {

    private final LembreteService lembreteService;
    private final UsuarioService usuarioService;

    // yyyy-MM-dd'T'HH:mm (formato do input datetime-local)
    private static final DateTimeFormatter FORMATO_HTML5 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public LembreteController(LembreteService lembreteService, UsuarioService usuarioService) {
        this.lembreteService = lembreteService;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/salvar")
    public String salvar(@RequestParam String titulo,
                         @RequestParam String descricao,
                         @RequestParam String dataHora,
                         Principal principal,
                         RedirectAttributes ra) {
        try {
            if (principal == null) return "redirect:/login";
            var quando = LocalDateTime.parse(dataHora, FORMATO_HTML5);
            if (quando.isBefore(LocalDateTime.now())) {
                ra.addFlashAttribute("erro", "A data e hora precisam estar no futuro.");
                return "redirect:/";
            }
            lembreteService.salvar(principal.getName(), titulo, descricao, quando);
            ra.addFlashAttribute("ok", "Lembrete salvo!");
        } catch (Exception e) {
            ra.addFlashAttribute("erro", "Erro: " + e.getMessage());
        }
        return "redirect:/";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, Principal principal, RedirectAttributes ra) {
        try {
            if (principal == null) return "redirect:/login";
            lembreteService.excluirSePertence(id, principal.getName());
            ra.addFlashAttribute("ok", "Excluído!");
        } catch (Exception e) {
            ra.addFlashAttribute("erro", "Erro: " + e.getMessage());
        }
        return "redirect:/";
    }
}
