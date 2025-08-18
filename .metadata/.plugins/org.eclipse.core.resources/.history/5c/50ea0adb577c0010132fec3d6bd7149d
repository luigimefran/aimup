package com.aimup.controller;

import com.aimup.model.Mensagem;
import com.aimup.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping("/chat")
    public String chat(Model model) {
        List<Mensagem> mensagens = chatService.getMensagensRecentes();
        model.addAttribute("mensagens", mensagens);
        model.addAttribute("qtdMembros", chatService.getQtdUsuarios());
        return "chat/chat";
    }

    @PostMapping("/chat/enviar")
    public String enviarMensagem(@RequestParam String mensagem, Principal principal) {
        String autor = principal.getName();
        chatService.salvarMensagem(autor, mensagem);
        return "redirect:/chat";
    }
}