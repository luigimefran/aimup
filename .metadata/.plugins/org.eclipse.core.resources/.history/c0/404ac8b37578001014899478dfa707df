package com.aimup.controller;

import com.aimup.model.Usuario;
import com.aimup.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String login() {
        return "login/login";
    }

    @GetMapping("/cadastro")
    public String cadastro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastro/cadastro";
    }

    @PostMapping("/cadastro")
    public String salvarCadastro(@ModelAttribute("usuario") @Valid Usuario usuario, Model model) {
        if (usuarioService.emailExistente(usuario.getEmail())) {
            model.addAttribute("erro", "Email já cadastrado");
            return "cadastro/cadastro";
        }
        usuarioService.salvarUsuario(usuario);
        return "redirect:/login?sucesso";
    }
}