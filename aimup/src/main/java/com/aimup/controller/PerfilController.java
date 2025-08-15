package com.aimup.controller;

import com.aimup.model.Usuario;
import com.aimup.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class PerfilController {

    private final UsuarioService usuarioService;

    public PerfilController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/perfil")
    public String perfil(Model model, Principal principal) {
        if (principal == null) {

        	return "redirect:/login";
        }
        String email = principal.getName();
        Usuario usuario = usuarioService.buscarPorEmail(email); 
        model.addAttribute("usuario", usuario); 
        return "perfil/perfil";
    }
}
