package com.aimup.controller;

import com.aimup.model.Usuario;
import com.aimup.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.security.Principal;

@Controller
public class PerfilController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/perfil")
    public String perfil(Model model, Principal principal) {
        String email = principal.getName();
        Usuario usuario = usuarioService.buscarPorEmail(email);
        model.addAttribute("usuario", usuario);
        return "perfil/perfil";
    }

    @PostMapping("/perfil/salvar")
    public String salvarPerfil(@ModelAttribute Usuario usuarioEditado, Principal principal) {
        String email = principal.getName();
        usuarioService.atualizarPerfil(email, usuarioEditado);
        return "redirect:/perfil?sucesso";
    }
}