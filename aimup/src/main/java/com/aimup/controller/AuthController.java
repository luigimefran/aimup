package com.aimup.controller;

import com.aimup.model.Usuario;
import com.aimup.service.UsuarioService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @ModelAttribute("usuarioForm")
    public Usuario prepararForm() {
        return new Usuario();
    }

    @GetMapping("/login")
    public String login() {
        return "login/login";
    }

    @GetMapping({"/cadastro", "/cadastro/"})
    public String cadastroForm() {
        return "cadastro/cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastrar(
            @Valid @ModelAttribute("usuarioForm") Usuario usuarioForm,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "cadastro/cadastro";
        }

        if (usuarioService.emailExistente(usuarioForm.getEmail())) {
            result.rejectValue("email", "email.existente", "Já existe usuário com esse e-mail.");
            return "cadastro/cadastro";
        }

        try {
            usuarioService.salvarUsuario(usuarioForm); // service já faz o BCrypt
        } catch (IllegalArgumentException e) {
            result.reject("cadastro.erro", e.getMessage());
            return "cadastro/cadastro";
        }

        return "redirect:/login?registered";
    }
    
    @GetMapping("/esqueceuSenha")
    public String esqueceuSenhaForm(Model model,
                                    @RequestParam(value = "sent", required = false) String sent) {
        model.addAttribute("sent", sent != null);
        return "esqueceuSenha/esqueceuSenha";
    }

    @PostMapping("/esqueceuSenha")
    public String processaEsqueceuSenha(@RequestParam("email") String email,
                                        HttpServletRequest request) {

    	String token = java.util.UUID.randomUUID().toString();

        String baseUrl = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        String link = baseUrl + "/reset-senha?token=" + token;

        System.out.println("Enviando email de reset para: " + email);
        System.out.println("Link de redefinição: " + link);

        return "redirect:/esqueceuSenha?sent=1";
    }

    @GetMapping("/reset-senha")
    public String resetSenha(@RequestParam("token") String token, Model model) {
        model.addAttribute("token", token);
        return "esqueceuSenha/resetSenhaDemo"; 
    }
}
