package com.aimup.controller;

import com.aimup.model.Usuario;
import com.aimup.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.security.Principal;

@Controller
public class PerfilController {

    private final UsuarioService usuarioService;

    public PerfilController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/perfil")
    public String perfil(Model model, Principal principal) {
        if (principal == null) return "redirect:/login";
        String email = principal.getName();
        Usuario usuario = usuarioService.buscarPorEmail(email);
        model.addAttribute("usuario", usuario);
        return "perfil/perfil";
    }

    @PostMapping("/perfil/foto")
    public String atualizarFoto(@RequestParam("foto") MultipartFile foto,
                                Principal principal,
                                RedirectAttributes ra) {
        try {
            if (principal == null) return "redirect:/login";
            Usuario u = usuarioService.buscarPorEmail(principal.getName());
            if (u == null) throw new IllegalArgumentException("Usuário não encontrado.");

            if (!foto.isEmpty()) {
                Path dir = Paths.get("uploads");
                Files.createDirectories(dir);
                // nome único e simples
                String original = foto.getOriginalFilename() == null ? "foto" : foto.getOriginalFilename().replaceAll("[^a-zA-Z0-9_.-]", "_");
                String nome = "user-" + u.getId() + "-" + System.currentTimeMillis() + "-" + original;
                Path destino = dir.resolve(nome);
                Files.copy(foto.getInputStream(), destino, StandardCopyOption.REPLACE_EXISTING);
                u.setFotoPerfil("/uploads/" + nome);
                usuarioService.salvarUsuarioSemTrocarSenha(u);
            }
            ra.addFlashAttribute("ok", "Foto atualizada!");
        } catch (Exception e) {
            ra.addFlashAttribute("erro", "Erro ao atualizar a foto: " + e.getMessage());
        }
        return "redirect:/perfil";
    }
}
