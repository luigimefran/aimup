package com.aimup.controller;

import com.aimup.model.Usuario;
import com.aimup.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RankingController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/ranking")
    public String exibirRanking(Model model) {
        List<Usuario> usuarios = usuarioService.listarPorPontuacaoDesc();
        model.addAttribute("usuarios", usuarios);
        return "ranking/ranking";
    }
}